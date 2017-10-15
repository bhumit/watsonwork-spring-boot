package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watsonwork.JiraApiProperties;
import com.ibm.watsonwork.MessageTypes;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.model.Actor;
import com.ibm.watsonwork.model.Annotation;
import com.ibm.watsonwork.model.AnnotationPayload;
import com.ibm.watsonwork.model.Entity;
import com.ibm.watsonwork.model.ExtractedInfoResponse;
import com.ibm.watsonwork.model.GraphQLQuery;
import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.model.jira.User;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;
import com.ibm.watsonwork.schema.WatsonWorkSchema.QueryResponse;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import com.ibm.watsonwork.service.JiraService;
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
import static com.ibm.watsonwork.schema.WatsonWorkSchema.*;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AddFocusInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AnnotationWrapperInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.AttachmentInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.ButtonStyle;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.CreateTargetedMessageInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.GenericAnnotationInput;
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

    private static final String JIRA_TRIGGER = "/jira";
    public static final String CONVERSATION = "Conversation";
    public static final String PRIORITY = "priority";
    public static final String STATUS = "jira-status";
    private static PrettyTime prettyTime = new PrettyTime();

    @Autowired
    private GraphQLClient graphQLClient;

    @Autowired
    private AuthService authService;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JiraApiProperties jiraApiProperties;

    @Autowired
    private WatsonWorkService watsonWorkService;

    private static ConversationService service = new ConversationService("2017-05-26");

    private static String WC_WORKSPACE_ID = "5fa3df53-f121-4308-8903-1c795e2d8abd";


    @Override
    @SneakyThrows(IOException.class)
    @Async
    public CompletableFuture<TargetedMessageMutation> processActionSelectedEvent(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());

        if(annotationPayload.getActionId().equalsIgnoreCase(String.format("%s help", JIRA_TRIGGER))) {
            return processActionSelectedActionTriggerEventHelp(event);
        }

        if(annotationPayload.getActionId().startsWith(JIRA_TRIGGER)) {
            return processActionSelectedActionTriggerEvent(event);
        }

        if(annotationPayload.getActionId().startsWith("share-")) {
            return CompletableFuture.completedFuture(processActionSelectedShareButton(event, annotationPayload));
        }


        if(annotationPayload.getActionId().startsWith("view-")) {
            return CompletableFuture.completedFuture(processActionSelectedViewButton(event, annotationPayload));
        }

        if(annotationPayload.getActionId().startsWith("assignto-")) {
            return CompletableFuture.completedFuture(processActionSelectedListAssigneesButton(event, annotationPayload));
        }

        if(annotationPayload.getActionId().startsWith("assign-")) {
            return CompletableFuture.completedFuture(processActionSelectedAssignIssueButton(event, annotationPayload));
        }

        if(annotationPayload.getActionId().startsWith("viewuser-")) {
            return CompletableFuture.completedFuture(processActionSelectedViewUserButton(event, annotationPayload));
        }

        if(annotationPayload.getActionId().startsWith("sharehelp")) {
            return CompletableFuture.completedFuture(processActionSelectedShareHelpButton(event, annotationPayload));
        }

        return processActionSelectedJiraAction(event, annotationPayload);
    }



    private CompletableFuture<TargetedMessageMutation> processActionSelectedJiraAction(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        Message message = getMessage(annotationPayload.getReferralMessageId());

        AnnotationPayload messageFocus = getAnnotationPayload(message);

        ExtractedInfoResponse extractedInfo = messageFocus.getExtractedInfo();

        String priority = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals(PRIORITY))
                .map(Entity::getText)
                .findFirst().orElse("");

        String jiraStatus = CollectionUtils.emptyIfNull(extractedInfo.getEntities()).stream()
                .filter(e -> e.getSource().equals(CONVERSATION))
                .filter(e -> e.getType().equals(STATUS))
                .map(Entity::getText)
                .findFirst().orElse("all");


        Response<MutationResponse> execution = sendTargetedMessageWithCards(event, annotationPayload, priority, jiraStatus);

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
                        .replace(JIRA_TRIGGER, "")
                        .replaceAll("_", " "))
                .build();

        MessageResponse response = service
                .message(WC_WORKSPACE_ID, newMessage)
                .execute();

        Optional<com.ibm.watson.developer_cloud.conversation.v1.model.Entity> priorityEntity = CollectionUtils.emptyIfNull(response.getEntities())
                .stream()
                .filter(i -> i.getEntity().equalsIgnoreCase(PRIORITY))
                .findFirst();

        Optional<com.ibm.watson.developer_cloud.conversation.v1.model.Entity> statusEntity = CollectionUtils.emptyIfNull(response.getEntities())
                .stream()
                .filter(i -> i.getEntity().equalsIgnoreCase(STATUS))
                .findFirst();

        String priority = "";

        if(priorityEntity.isPresent()) {
            priority = priorityEntity.get().getValue();
        }

        String status = "all";

        if(statusEntity.isPresent()) {
            status = statusEntity.get().getValue();
        }

        Response<MutationResponse> execution = sendTargetedMessageWithCards(event, annotationPayload, priority, status);
        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());

    }

    @SneakyThrows(IOException.class)
    private CompletableFuture<TargetedMessageMutation> processActionSelectedActionTriggerEventHelp(WebhookEvent event) {
        AnnotationPayload annotationPayload = MessageUtils.mapAnnotationPayload(event.getAnnotationPayload());



        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput(HELP_TEXT)
        .setButtons(Arrays.asList(new ButtonWrapperInput(
                new PostbackButtonInput("sharehelp", "Share", ButtonStyle.PRIMARY)))));
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId(),
                Collections.singletonList(annotationWrapperInput));

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        Response<MutationResponse> execution = graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
        return CompletableFuture.completedFuture(execution.body().getData().getCreateTargetedMessage());
    }

    private Response<MutationResponse> sendTargetedMessageWithCards(WebhookEvent event, AnnotationPayload annotationPayload, String priority, String status) throws IOException {

        List<Issue> issues = null;

        if(Objects.equals(status, "all")) {
            issues = jiraService.getAllByPriority(priority);
        }

        if(Objects.equals(status, "unassigned")) {
            issues = jiraService.getUnassignedByPriority(priority);

        }
        if(Objects.equals(status, "assigned")) {
            issues = jiraService.getAssignedByPriority(priority);
        }

        List<AttachmentInput> attachments = CollectionUtils.emptyIfNull(issues)
                .stream()
                .map(this::buildAttachment)
                .collect(Collectors.toList());

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("No issues found.. oops."));
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
        actor.setName(Humanize.titleize(event.getUserName()) + " shared issue:");
        actor.setUrl("https://api.watsonwork.ibm.com/photos/" + event.getUserId());
        annotation.setActor(actor);


        watsonWorkService.createMessage(event.getSpaceId(), MessageUtils.buildMessage(annotation));

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("Issue details shared with the space."));
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

    private TargetedMessageMutation processActionSelectedListAssigneesButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");
        String issueKey = annotationPayload.getActionId().replaceFirst("assignto-", "");

        List<User> assignableUsers = jiraService.getAssignableUsers(issueKey);

        List<AttachmentInput> attachments = CollectionUtils.emptyIfNull(assignableUsers)
                .stream()
                .map(i -> buildAttachmentUser(i, issueKey))
                .collect(Collectors.toList());


        GenericAnnotationInput genericAnnotationInput = new GenericAnnotationInput("fetching users..");

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(genericAnnotationInput);
        CreateTargetedMessageInput targetedMessageInput = new CreateTargetedMessageInput(event.getSpaceId(),
                annotationPayload.getTargetDialogId(), event.getUserId(),
                Collections.singletonList(annotationWrapperInput))
                .setAttachments(attachments);

        String mutationToExecute = mutation(query -> query.createTargetedMessage(targetedMessageInput, TargetedMessageMutationQuery::successful)).toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        Response<MutationResponse> execution = graphQLClient.createTargetedMessage(authService.getAppAuthToken(), graphQLQuery).execute();
        return execution.body().getData().getCreateTargetedMessage();
    }

    private TargetedMessageMutation processActionSelectedAssignIssueButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");
        String[] split = annotationPayload.getActionId().replaceFirst("assign-", "").split(",");
        String userKey = split[0];
        String issueKey = split[1];

        jiraService.assignIssue(userKey, issueKey);

        // send message in space
        Annotation annotation = new Annotation();
        annotation.setColor("#3b7fc4");
        annotation.setText(String.format("[See Issue %s](%s)",issueKey, jiraApiProperties.getApiUrl() + "/browse/" + issueKey));
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);

        Actor actor = new Actor();
        actor.setName(Humanize.titleize(event.getUserName()) + " assigned issue " + issueKey + " to " +  userKey);
        annotation.setActor(actor);

        watsonWorkService.createMessage(event.getSpaceId(), MessageUtils.buildMessage(annotation));



        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput(String.format("Issue [%s](%s) assigned to %s", issueKey, jiraApiProperties.getApiUrl() + "/browse/" + issueKey,  userKey)));
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

    private TargetedMessageMutation processActionSelectedShareHelpButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        // send message in space
        Annotation annotation = new Annotation();
        annotation.setColor("#3b7fc4");
        annotation.setText(HELP_TEXT);
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);

        Actor actor = new Actor();
        actor.setName(Humanize.titleize(event.getUserName()) + " shared info on how to use JIRA app");
        annotation.setActor(actor);

        watsonWorkService.createMessage(event.getSpaceId(), MessageUtils.buildMessage(annotation));

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(new GenericAnnotationInput("Help info shared with space."));
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

    private TargetedMessageMutation processActionSelectedViewUserButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");
        String[] split = annotationPayload.getActionId().replaceFirst("viewuser-", "").split(",");
        String userKey = split[0];
        String issueKey = split[1];

        List<Issue> assignedToUser = jiraService.getAssignedToUser(userKey);

        String title = "All issues assigned to " + userKey;

        StringBuilder text = new StringBuilder();

        for (Issue issue : assignedToUser) {
            text
                    .append(String.format("*[%s](%s)* - ", issue.getKey(),jiraApiProperties.getApiUrl()  + "/browse/" + issue.getKey()))
                    .append(issue.getSummary())
                    .append(" | ")
                    .append("*")
                    .append(issue.getPriority().getName())
                    .append("*")
                    .append("\n");
        }


        List<ButtonWrapperInput> buttonInputs = Collections.singletonList(new ButtonWrapperInput(new PostbackButtonInput(annotationPayload.getActionId().replaceFirst("viewuser-","assign-"), "Assign " + issueKey , ButtonStyle.PRIMARY)));
        GenericAnnotationInput genericAnnotationInput = new GenericAnnotationInput(text.toString())
                .setTitle(title)
                .setButtons(buttonInputs);

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(genericAnnotationInput);
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

    private TargetedMessageMutation processActionSelectedViewButton(WebhookEvent event, AnnotationPayload annotationPayload) throws IOException {
        log.info("processing actionSelected event...");

        String[] split = annotationPayload.getActionId().replaceFirst("view-", "").split(",");
        String issueKey = split[0];

        Annotation annotation = objectMapper.readValue(annotationPayload.getActionId().replaceFirst("view-" + issueKey + ",",""), Annotation.class);

        ButtonWrapperInput shareButton = new ButtonWrapperInput(new PostbackButtonInput(annotationPayload.getActionId().replaceFirst("view-" + issueKey + ",","share-"), "Share", ButtonStyle.PRIMARY));
        ButtonWrapperInput assignToButton = new ButtonWrapperInput(new PostbackButtonInput("assignto-" +  issueKey, "Assign", ButtonStyle.SECONDARY));

        List<ButtonWrapperInput> buttonInputs = Arrays.asList(shareButton, assignToButton);
        GenericAnnotationInput genericAnnotationInput = new GenericAnnotationInput(annotation.getText())
                .setTitle(annotation.getTitle())
                .setColor(annotation.getColor())
                .setButtons(buttonInputs);

        AnnotationWrapperInput annotationWrapperInput = new AnnotationWrapperInput(genericAnnotationInput);
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
    private AttachmentInput buildAttachment(Issue issue) {
        AttachmentInput attachmentInput = new AttachmentInput(AttachmentType.CARD);

        CardInput cardInput = new CardInput(CardType.INFORMATION);

        String annotationDescription = StringUtils.defaultIfEmpty(
                "*Description:* " + issue.getDescription()
                        + "\n"
                        + "*Status:* " + issue.getStatus().getName()
                        + "\n"
                        + "*Issue Type:* " + issue.getIssueType().getName()
                        + "\n"
                        + "*Reporter:* " + issue.getReporter().getDisplayName()
                        + "\n"
                        + "*Updated:* " + prettyTime.format(issue.getUpdateDate().toDate())
                , "No description found.");


        String description = StringUtils.defaultIfEmpty(issue.getDescription(), "No description found.");
        String author = StringUtils.defaultIfEmpty(issue.getReporter().getDisplayName(), "Anonymous");
        String title = StringUtils.defaultIfEmpty(String.format("[%s] ", issue.getKey()) + issue.getSummary(), "No title found for the issue.");
        String url = StringUtils.defaultIfEmpty(jiraApiProperties.getApiUrl() + "/browse/" + issue.getKey(), "No URL found.");
        Date published = issue.getUpdateDate().toDate();
        String publishedAt = String.valueOf(published.getTime());

        Annotation annotation = new Annotation();
        annotation.setColor("#3b7fc4");
        annotation.setText(annotationDescription + "\n" + "[See Issue]" + "(" + url + ")");
        annotation.setTitle(title);
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);


        String payload = objectMapper.writeValueAsString(annotation);

        ButtonInput shareUrlButton = new ButtonInput("Share", "share-" + payload, ButtonStyle.SECONDARY);
        ButtonInput viewButton = new ButtonInput("See More", "view-"+ issue.getKey() + "," + payload, ButtonStyle.PRIMARY);
        ButtonInput assignButton = new ButtonInput("Assign", "assignto-" + issue.getKey(), ButtonStyle.PRIMARY);

        InformationCardInput informationCardInput = new InformationCardInput(title, author, description, publishedAt,
                Arrays.asList(viewButton, assignButton, shareUrlButton));
        cardInput.setInformationCardInput(informationCardInput);

        attachmentInput.setCardInput(cardInput);

        return attachmentInput;
    }


    @SneakyThrows
    private AttachmentInput buildAttachmentUser(User user, String issueNumber) {
        AttachmentInput attachmentInput = new AttachmentInput(AttachmentType.CARD);

        CardInput cardInput = new CardInput(CardType.INFORMATION);

        String userIssueDescription = "Timezone: " + user.getTimeZone();

        String description = StringUtils.defaultIfEmpty(userIssueDescription, "No description found.");
        String author = StringUtils.defaultIfEmpty(user.getKey(), "Anonymous");
        String title = StringUtils.defaultIfEmpty(user.getDisplayName(), "No title found for the issue.");
        String publishedAt = String.valueOf(new Date().getTime());

        ButtonInput assignButton = new ButtonInput("Assign", "assign-" + user.getKey() + "," + issueNumber, ButtonStyle.PRIMARY);
        ButtonInput viewUserButton = new ButtonInput("View User", "viewuser-" + user.getKey() + "," + issueNumber, ButtonStyle.SECONDARY);

        InformationCardInput informationCardInput = new InformationCardInput(title, author, description, publishedAt,
                Arrays.asList(viewUserButton, assignButton));
        cardInput.setInformationCardInput(informationCardInput);

        attachmentInput.setCardInput(cardInput);

        return attachmentInput;
    }
}
