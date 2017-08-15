package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watsonwork.MessageTypes;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.model.Actor;
import com.ibm.watsonwork.model.Annotation;
import com.ibm.watsonwork.model.AnnotationPayload;
import com.ibm.watsonwork.model.Entity;
import com.ibm.watsonwork.model.ExtractedInfoResponse;
import com.ibm.watsonwork.model.GraphQLQuery;
import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.model.zendesk.Status;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;
import com.ibm.watsonwork.schema.WatsonWorkSchema.QueryResponse;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import com.ibm.watsonwork.service.WatsonWorkService;
import com.ibm.watsonwork.utils.MessageUtils;
import com.shopify.graphql.support.ID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;
import retrofit2.Response;

import static com.ibm.watsonwork.MessageTypes.MESSAGE_FOCUS;
import static com.ibm.watsonwork.utils.MessageUtils.REGEX_NEW_TICKET;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.*;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AddFocusInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AnnotationWrapperInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AttachmentInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AttachmentType;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.ButtonInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.ButtonStyle;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.CardInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.CardType;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.CreateTargetedMessageInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.GenericAnnotationInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.InformationCardInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.MessageFocusInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.MessageMutation;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.MutationResponse;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.TargetedMessageMutation;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.TargetedMessageMutationQuery;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.mutation;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.query;

@Slf4j
@Service
@EnableAsync
public class DefaultGraphQLService implements GraphQLService {

    private static final String CONVERSATION = "Conversation";

    @Autowired
    private GraphQLClient graphQLClient;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WatsonWorkService watsonWorkService;

    @Autowired
    private Zendesk zendesk;

    @Override
    @SneakyThrows(IOException.class)
    @Async
    public CompletableFuture<TargetedMessageMutation> processActionSelectedEvent(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());

        if (annotationPayload.getActionId().startsWith("share-")) {
            return CompletableFuture.completedFuture(processActionSelectedShareButtonAnnotation(event, annotationPayload));
        }

        if(annotationPayload.getActionId().equals("show_all")) {
            return sendTargetedMessageShowTicketsByStatus(event, annotationPayload);
        }

        return sendTargetedMessageShowTicketDetails(event, annotationPayload);
    }

    private CompletableFuture<TargetedMessageMutation> sendTargetedMessageShowTicketDetails(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        Message message = getMessage(annotationPayload.getReferralMessageId());

        AnnotationPayload messageFocus = getAnnotationPayload(message);

        ExtractedInfoResponse extractedInfo = messageFocus.getExtractedInfo();

        String ticketNumber = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals("sys-number"))
                .map(Entity::getText)
                .findFirst().orElse("");


        Response<MutationResponse> execution = createAndSendTargetedMessageWithAnnotation(event, annotationPayload, Long.valueOf(ticketNumber));

        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());
    }

    private CompletableFuture<TargetedMessageMutation> sendTargetedMessageShowTicketsByStatus(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        Message message = getMessage(annotationPayload.getReferralMessageId());

        AnnotationPayload messageFocus = getAnnotationPayload(message);

        ExtractedInfoResponse extractedInfo = messageFocus.getExtractedInfo();

        String status = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals("status"))
                .map(Entity::getText)
                .findFirst().orElse("");


        Response<MutationResponse> execution = createAndSendTargetedMessageWithCards(event, annotationPayload, status);

        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());
    }

    private AnnotationPayload getAnnotationPayload(Message message) {
        Optional<AnnotationPayload> first = message.getAnnotations()
                .stream()
                .map(MessageUtils::mapAnnotationPayload)
                .filter(payload -> payload.getType().equals(MESSAGE_FOCUS) && payload.getApplicationId().equals(authService.getAppId()))
                .findFirst();


        AnnotationPayload messageFocus = new AnnotationPayload();
        if(first.isPresent()) {
            messageFocus = first.get();
        }
        return messageFocus;
    }

    private Response<MutationResponse> createAndSendTargetedMessageWithAnnotation(WebhookEvent event, AnnotationPayload annotationPayload, Long ticketNumber) throws IOException {
        Ticket ticket = zendesk.getTicket(ticketNumber);

        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId());

        if(ticket == null) {
            GenericAnnotationInput genericAnnotation = new GenericAnnotationInput("Ticket #" + ticketNumber + " is either deleted, or is invalid.");
            targetedMessageInput.setAnnotations(Collections.singletonList(new AnnotationWrapperInput(genericAnnotation)));
        } else {
            targetedMessageInput.setAnnotations(Collections.singletonList(buildAnnotation(ticket, zendesk.getUser(ticket.getAssigneeId()).getName())));
        }


        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        return graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
    }

    private Response<MutationResponse> createAndSendTargetedMessageWithCards(WebhookEvent event, AnnotationPayload annotationPayload, String status) throws IOException {
        Iterable<Ticket> tickets = zendesk.getTicketsFromSearch(String.format("status:%s", status));

        List<AttachmentInput> attachments = new ArrayList<>();

        tickets.forEach(ticket -> attachments.add(buildAttachment(ticket, zendesk.getUser(ticket.getAssigneeId()).getName())));

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("upgrade your client to use cards..."));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId())
                .setAttachments(attachments)
                .setAnnotations(Collections.singletonList(annotationWrapperInput));

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        return graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
    }

    private TargetedMessageMutation processActionSelectedShareButtonAnnotation(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");
        Annotation annotation = objectMapper.readValue(annotationPayload.getActionId().replaceFirst("share-",""), Annotation.class);


        watsonWorkService.createMessage(event.getSpaceId(), MessageUtils.buildMessage(annotation));

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("Ticket shared with this space."));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId())
                .setAnnotations(Collections.singletonList(annotationWrapperInput))
                .setAttachments(Collections.emptyList());

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        Response<MutationResponse> execution = graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
        return execution.body().getData().getCreateTargetedMessage();
    }

    @Override
    @SneakyThrows(IOException.class)
    public Message getMessage(String messageId) {
        String queryToSend = query(rootQuery -> rootQuery
                .message(new ID(messageId), query -> {
                    query
                            .content()
                            .annotations()
                            .contentType()
                            .created();

                })).toString();
        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(queryToSend);

        Response<QueryResponse> execution = graphQLClient.getMessage(authService.getAppAuthToken(), graphQLQuery).execute();
        QueryResponse body = execution.body();

        Message message = new Message();
        if (execution.isSuccessful()) {
            message = body.getData().getMessage();
            log.info("message fetched by id {}", messageId);
        }
        return message;
    }

    @SneakyThrows
    private AttachmentInput buildAttachment(Ticket ticket, String assigneeName) {
        AttachmentInput attachmentInput = new AttachmentInput(AttachmentType.CARD);

        CardInput cardInput = new CardInput(CardType.INFORMATION);

        Optional<Status> first = Arrays.stream(Status.values()).filter(e -> e.name().equalsIgnoreCase(ticket.getStatus().name())).findFirst();

        Annotation annotation = new Annotation();
        annotation.setColor(first.get().getValue());
        String text = ticket.getDescription().replaceFirst(REGEX_NEW_TICKET.pattern(), "");
        annotation.setText(String.format("%s\n[View Ticket](%s)", text, "https://watsonworkdemo.zendesk.com/agent/tickets/" + ticket.getId()));
        annotation.setTitle(ticket.getSubject());
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);
        Actor actor = new Actor();
        actor.setName(String.format("#%s Assigned to %s - %s", ticket.getId(), assigneeName, ticket.getStatus().name()));
        annotation.setActor(actor);


        String payload = objectMapper.writeValueAsString(annotation);

        ButtonInput shareUrlButton = new ButtonInput("Share With Space", "share-" + payload, ButtonStyle.PRIMARY);

        InformationCardInput informationCardInput = new InformationCardInput(String.format("#%d %s", ticket.getId(), ticket.getSubject()), assigneeName, ticket.getDescription(), String.valueOf(ticket.getCreatedAt().getTime()),
                Collections.singletonList(shareUrlButton));
        cardInput.setInformationCardInput(informationCardInput);

        attachmentInput.setCardInput(cardInput);

        return attachmentInput;
    }

    @SneakyThrows
    private AnnotationWrapperInput buildAnnotation(Ticket ticket, String assigneeName) {
        String text = ticket.getDescription().replaceFirst(REGEX_NEW_TICKET.pattern(), "");
        String actorName = String.format("#%s Assigned to %s - %s", ticket.getId(), assigneeName, ticket.getStatus().name());
        String title = ticket.getSubject();
        Optional<Status> status = Arrays.stream(Status.values()).filter(e -> e.name().equalsIgnoreCase(ticket.getStatus().name())).findFirst();
        String colour = status.get().getValue();

        GenericAnnotationInput genericAnnotation = new GenericAnnotationInput(text);
        genericAnnotation.setTitle(title);
        genericAnnotation.setActor(new ActorInput().setName(actorName));
        genericAnnotation.setColor(colour);


        Annotation annotation = new Annotation();
        String annotationText = String.format("%s\n[View Ticket](%s)", genericAnnotation.getText(), "https://watsonworkdemo.zendesk.com/agent/tickets/" + ticket.getId());
        annotation.setColor(genericAnnotation.getColor());
        annotation.setText(annotationText);
        annotation.setTitle(genericAnnotation.getTitle());
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);
        Actor actor = new Actor();
        actor.setName(genericAnnotation.getActor().getName());
        annotation.setActor(actor);

        String payload = objectMapper.writeValueAsString(annotation);

        PostbackButtonInput postbackButtonInput = new PostbackButtonInput("share-" + payload,"Share with Space", ButtonStyle.PRIMARY);
        ButtonWrapperInput shareButton = new ButtonWrapperInput(postbackButtonInput);

        genericAnnotation.setButtons(Collections.singletonList(shareButton));
        return new AnnotationWrapperInput(genericAnnotation);
    }

}
