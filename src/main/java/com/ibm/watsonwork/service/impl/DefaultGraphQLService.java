package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watsonwork.MessageTypes;
import com.ibm.watsonwork.NewsApiProperties;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.model.Actor;
import com.ibm.watsonwork.model.Annotation;
import com.ibm.watsonwork.model.AnnotationPayload;
import com.ibm.watsonwork.model.Article;
import com.ibm.watsonwork.model.Entity;
import com.ibm.watsonwork.model.ExtractedInfoResponse;
import com.ibm.watsonwork.model.GraphQLQuery;
import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.schema.WatsonWorkSchema;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;
import com.ibm.watsonwork.schema.WatsonWorkSchema.QueryResponse;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import com.ibm.watsonwork.service.NewsService;
import com.ibm.watsonwork.service.WatsonWorkService;
import com.ibm.watsonwork.utils.MessageUtils;
import com.shopify.graphql.support.ID;
import humanize.Humanize;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import static com.ibm.watsonwork.MessageTypes.MESSAGE_FOCUS;
import static com.ibm.watsonwork.WatsonWorkConstants.HELP_TEXT;
import static com.ibm.watsonwork.WatsonWorkConstants.SOURCE_TEXT;
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

    private static final String NEWS_ = "news_";
    private static final String NEWS_TRIGGER = "/news";
    public static final String CONVERSATION = "Conversation";
    public static final String SOURCE = "source";
    public static final String BBC_NEWS = "bbc-news";
    public static final String SORT_BY = "sortBy";
    private static PrettyTime prettyTime = new PrettyTime();

    @Autowired
    private GraphQLClient graphQLClient;

    @Autowired
    private AuthService authService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WatsonWorkService watsonWorkService;

    private static ConversationService service = new ConversationService("2017-05-26");

    private static String WC_WORKSPACE_ID = "abb5a27f-ef48-444c-bdd2-a5fae61359fd";


    @Override
    @SneakyThrows(IOException.class)
    @Async
    public CompletableFuture<TargetedMessageMutation> processActionSelectedEvent(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());

        if(annotationPayload.getActionId().equalsIgnoreCase(String.format("%s help", NEWS_TRIGGER))) {
            return processActionSelectedActionTriggerEventHelp(event);
        }

        if(annotationPayload.getActionId().startsWith(NEWS_TRIGGER)) {
            return processActionSelectedActionTriggerEvent(event);
        }

        if(annotationPayload.getActionId().startsWith("share-")) {
            return CompletableFuture.completedFuture(processActionSelectedShareButton(event, annotationPayload));
        }

        return processActionSelectedNewsAction(event, annotationPayload);
    }

    private CompletableFuture<TargetedMessageMutation> processActionSelectedNewsAction(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        Message message = getMessage(annotationPayload.getReferralMessageId());

        AnnotationPayload messageFocus = getAnnotationPayload(message);

        ExtractedInfoResponse extractedInfo = messageFocus.getExtractedInfo();

        String source = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals(SOURCE))
                .map(Entity::getText)
                .findFirst().orElse(BBC_NEWS);

        String sortBy = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals(SORT_BY))
                .map(Entity::getText)
                .findFirst().orElse("top");


        Response<MutationResponse> execution = sendTargetedMessageWithCards(event, annotationPayload, source, sortBy);

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

    @Override
    @SneakyThrows(IOException.class)
    public CompletableFuture<TargetedMessageMutation> processActionSelectedActionTriggerEvent(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());

        service.setUsernameAndPassword("7a6154ef-e40c-4b0c-9c44-afab2b827d02", "2IHX1mbh4QiS");
        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText(annotationPayload.getActionId()
                        .replace(NEWS_, "")
                        .replace(NEWS_TRIGGER, "")
                        .replaceAll("_", " "))
                .build();

        MessageResponse response = service
                .message(WC_WORKSPACE_ID, newMessage)
                .execute();

        Optional<com.ibm.watson.developer_cloud.conversation.v1.model.Entity> sourceEntity = CollectionUtils.emptyIfNull(response.getEntities())
                .stream()
                .filter(i -> i.getEntity().equalsIgnoreCase(SOURCE))
                .findFirst();

        Optional<com.ibm.watson.developer_cloud.conversation.v1.model.Entity> sortByEntity = CollectionUtils.emptyIfNull(response.getEntities())
                .stream()
                .filter(i -> i.getEntity().equalsIgnoreCase(SORT_BY))
                .findFirst();

        String source = "bbc-news";
        String sortBy = "top";

        if(sourceEntity.isPresent()) {
            source = sourceEntity.get().getValue();
        }

        if(sortByEntity.isPresent()) {
            sortBy = sortByEntity.get().getValue();
        }

        Response<MutationResponse> execution = sendTargetedMessageWithCards(event, annotationPayload, source, sortBy);
        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());

    }

    @SneakyThrows(IOException.class)
    private CompletableFuture<TargetedMessageMutation> processActionSelectedActionTriggerEventHelp(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());



        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput(HELP_TEXT)
        .setButtons(Arrays.asList(new WatsonWorkSchema.ButtonWrapperInput(
                new WatsonWorkSchema.PostbackButtonInput("share-" + SOURCE_TEXT, "Share sources", ButtonStyle.PRIMARY)))));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId(),
                Collections.singletonList(annotationWrapperInput));

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        Response<MutationResponse> execution = graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());
    }

    private Response<MutationResponse> sendTargetedMessageWithCards(WebhookEvent event, AnnotationPayload annotationPayload, String source, String sortBy) throws IOException {
        List<AttachmentInput> attachments = CollectionUtils.emptyIfNull(newsService.getLatestNews(source, sortBy).getArticles())
                .stream()
                .map(this::buildAttachment)
                .collect(Collectors.toList());

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("failed to fetch news article..."));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId(),
                Collections.singletonList(annotationWrapperInput))
                .setAttachments(attachments);

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        return graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
    }

    private TargetedMessageMutation processActionSelectedShareButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");
        Annotation annotation = objectMapper.readValue(annotationPayload.getActionId().replaceFirst("share-",""), Annotation.class);
        Actor actor = new Actor();
        actor.setAvatar("https://api.watsonwork.ibm.com/photos/" + event.getUserId());
        actor.setName(Humanize.titleize(event.getUserName()) + " shared article:");
        actor.setUrl("https://api.watsonwork.ibm.com/photos/" + event.getUserId());
        annotation.setActor(actor);


        watsonWorkService.createMessage(event.getSpaceId(), MessageUtils.buildMessage(annotation));

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("News article shared with this space."));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId(),
                Collections.singletonList(annotationWrapperInput))
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

    @Override
    @SneakyThrows(IOException.class)
    public MessageMutation addMessageFocus(String messageId) {

        MessageFocusInput messageFocus = new MessageFocusInput("news", "news")
                .setHidden(false)
                .setActions(Collections.singletonList("news"))
                .setCategory("news")
                .setPayload("news")
                .setEnd(4)
                .setStart(0)
                .setVersion(1)
                .setConfidence(0.9);

        AddFocusInput addFocusInput = new AddFocusInput(messageId, messageFocus);
        String queryToSend = mutation(query -> query.addMessageFocus(addFocusInput, query1 -> query1
                .message(messageQuery -> messageQuery.content().contentType().created()))).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(queryToSend);

        Response<MutationResponse> execution = graphQLClient.addMessageFocus(authService.getAppAuthToken(), graphQLQuery).execute();

        return execution.body().getData().getAddMessageFocus();
    }

    @SneakyThrows
    private AttachmentInput buildAttachment(Article article) {
        AttachmentInput attachmentInput = new AttachmentInput(AttachmentType.CARD);

        CardInput cardInput = new CardInput(CardType.INFORMATION);

        String description = StringUtils.defaultIfEmpty(article.getDescription(), "No description found.");
        String author = StringUtils.defaultIfEmpty(article.getAuthor(), "Unknown author.");
        String title = StringUtils.defaultIfEmpty(article.getTitle(), "No title found for the article.");
        String url = StringUtils.defaultIfEmpty(article.getUrl(), "No URL found.");
        Date published = (article.getPublishedAt() != null) ? article.getPublishedAt() : new Date();
        String publishedAt = String.valueOf(published.getTime());

        Annotation annotation = new Annotation();
        annotation.setColor("#E57478");
        annotation.setText(description + "\n" + "[View full coverage]" + "(" + url + ")");
        annotation.setTitle(title + " | " + prettyTime.format(published));
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);


        String payload = objectMapper.writeValueAsString(annotation);

        ButtonInput shareUrlButton = new ButtonInput("Share With Space", "share-" + payload, ButtonStyle.PRIMARY);

        InformationCardInput informationCardInput = new InformationCardInput(title, author, description, publishedAt,
                Collections.singletonList(shareUrlButton));
        cardInput.setInformationCardInput(informationCardInput);

        attachmentInput.setCardInput(cardInput);

        return attachmentInput;
    }
}
