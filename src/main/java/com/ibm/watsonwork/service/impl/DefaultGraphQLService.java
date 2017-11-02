package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.model.GraphQLQuery;
import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.schema.WatsonWorkSchema;
import com.ibm.watsonwork.schema.WatsonWorkSchema.CreateMessageInput;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;
import com.ibm.watsonwork.schema.WatsonWorkSchema.QueryResponse;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import com.shopify.graphql.support.ID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import static com.ibm.watsonwork.schema.WatsonWorkSchema.AnnotationWrapperInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.GenericAnnotationInput;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.mutation;
import static com.ibm.watsonwork.schema.WatsonWorkSchema.query;

@Slf4j
@Service
@EnableAsync
public class DefaultGraphQLService implements GraphQLService {

    @Autowired
    private GraphQLClient graphQLClient;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void processWebhookEvent(WebhookEvent event) {
        Message message = getMessage(event.getMessageId());

        message.getAnnotations().stream()
                .map(this::getJsonNode)
                .filter(Objects::nonNull)
                .filter(node -> node.get("type").asText().equals("message-nlp-docSentiment"))
                .forEach(node -> sendSentimentMessage(event, node));

    }

    @SneakyThrows(IOException.class)
    private void sendSentimentMessage(WebhookEvent event, JsonNode node) {
        CreateMessageInput createMessageInput = new CreateMessageInput(event.getSpaceId());
        createMessageInput.setAnnotations(Collections.singletonList(new AnnotationWrapperInput(
                new GenericAnnotationInput(WordUtils.capitalizeFully(event.getUserName()) + " sounds " + node.get("docSentiment").get("type").asText())
                        .setTitle("Sentiment Tone Analysis"))));

        String mutationToExecute = mutation(query -> query
                .createMessage(createMessageInput, q -> q.message(WatsonWorkSchema.MessageQuery::content)))
                .toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);
        graphQLClient.executeGraphqlMutation(authService.getAppAuthToken(), graphQLQuery).execute();
    }

    @SneakyThrows(IOException.class)
    private void sendAnnotatedPayloadMessage(WebhookEvent event, JsonNode node) {

        String jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        CreateMessageInput createMessageInput = new CreateMessageInput(event.getSpaceId())
                .setAnnotations(Collections.singletonList(new AnnotationWrapperInput(
                        new GenericAnnotationInput(jsonPayload).setTitle(node.get("type").textValue()))));

        String mutationToExecute = mutation(query -> query
                .createMessage(createMessageInput, q -> q.message(WatsonWorkSchema.MessageQuery::content)))
                .toString();

        GraphQLQuery graphQLQuery = new GraphQLQuery();
        graphQLQuery.setQuery(mutationToExecute);

        graphQLClient.executeGraphqlMutation(authService.getAppAuthToken(), graphQLQuery).execute();

    }


    @SneakyThrows(IOException.class)
    private JsonNode getJsonNode(String payload) {
        return objectMapper.readValue(payload, JsonNode.class);
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
}
