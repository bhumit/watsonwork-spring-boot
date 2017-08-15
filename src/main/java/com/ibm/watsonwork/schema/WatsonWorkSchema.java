// Generated from graphql_java_gen gem

package com.ibm.watsonwork.schema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.Query;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;

import com.shopify.graphql.support.ID;

import java.math.BigDecimal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WatsonWorkSchema {
    public static QueryRootQuery query(QueryRootQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("{");
        QueryRootQuery query = new QueryRootQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }

    public static class QueryResponse {
        private TopLevelResponse response;
        private QueryRoot data;

        public QueryResponse() {
        }

        public QueryResponse(TopLevelResponse response) throws SchemaViolationError {
            this.response = response;
            this.data = response.getData() != null ? new QueryRoot(response.getData()) : null;
        }

        public QueryRoot getData() {
            return data;
        }

        public List<Error> getErrors() {
            return response.getErrors();
        }

        public String toJson() {
            return new Gson().toJson(response);
        }

        public String prettyPrintJson() {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(response);
        }

        public static QueryResponse fromJson(String json) throws SchemaViolationError {
            final TopLevelResponse response = new Gson().fromJson(json, TopLevelResponse.class);
            return new QueryResponse(response);
        }
    }

    public static MutationRootQuery mutation(MutationRootQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("mutation{");
        MutationRootQuery query = new MutationRootQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }

    public static class MutationResponse {
        private TopLevelResponse response;
        private MutationRoot data;

        public MutationResponse(TopLevelResponse response) throws SchemaViolationError {
            this.response = response;
            this.data = response.getData() != null ? new MutationRoot(response.getData()) : null;
        }

        public MutationResponse() {
        }

        public MutationRoot getData() {
            return data;
        }

        public List<Error> getErrors() {
            return response.getErrors();
        }

        public String toJson() {
            return new Gson().toJson(response);
        }

        public String prettyPrintJson() {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(response);
        }

        public static MutationResponse fromJson(String json) throws SchemaViolationError {
            final TopLevelResponse response = new Gson().fromJson(json, TopLevelResponse.class);
            return new MutationResponse(response);
        }
    }

    public static class ActorInput implements Serializable {
        private String name;

        private String url;

        private String avatar;

        public String getName() {
            return name;
        }

        public ActorInput setName(String name) {
            this.name = name;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public ActorInput setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getAvatar() {
            return avatar;
        }

        public ActorInput setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            if (name != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("name:");
                Query.appendQuotedString(_queryBuilder, name.toString());
            }

            if (url != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("url:");
                Query.appendQuotedString(_queryBuilder, url.toString());
            }

            if (avatar != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("avatar:");
                Query.appendQuotedString(_queryBuilder, avatar.toString());
            }

            _queryBuilder.append('}');
        }
    }

    public static class AddFocusInput implements Serializable {
        private String messageId;

        private MessageFocusInput messageFocus;

        public AddFocusInput(String messageId, MessageFocusInput messageFocus) {
            this.messageId = messageId;

            this.messageFocus = messageFocus;
        }

        public String getMessageId() {
            return messageId;
        }

        public AddFocusInput setMessageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public MessageFocusInput getMessageFocus() {
            return messageFocus;
        }

        public AddFocusInput setMessageFocus(MessageFocusInput messageFocus) {
            this.messageFocus = messageFocus;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("messageId:");
            Query.appendQuotedString(_queryBuilder, messageId.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("messageFocus:");
            messageFocus.appendTo(_queryBuilder);

            _queryBuilder.append('}');
        }
    }

    public static class AddSpaceMembersInput implements Serializable {
        private ID spaceId;

        private List<SpaceMemberInput> members;

        public AddSpaceMembersInput(ID spaceId, List<SpaceMemberInput> members) {
            this.spaceId = spaceId;

            this.members = members;
        }

        public ID getSpaceId() {
            return spaceId;
        }

        public AddSpaceMembersInput setSpaceId(ID spaceId) {
            this.spaceId = spaceId;
            return this;
        }

        public List<SpaceMemberInput> getMembers() {
            return members;
        }

        public AddSpaceMembersInput setMembers(List<SpaceMemberInput> members) {
            this.members = members;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("spaceId:");
            Query.appendQuotedString(_queryBuilder, spaceId.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("members:");
            _queryBuilder.append('[');

            String listSeperator1 = "";
            for (SpaceMemberInput item1 : members) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                item1.appendTo(_queryBuilder);
            }
            _queryBuilder.append(']');

            _queryBuilder.append('}');
        }
    }

    public static class AnnotationWrapperInput implements Serializable {
        private GenericAnnotationInput genericAnnotation;

        public AnnotationWrapperInput(GenericAnnotationInput genericAnnotation) {
            this.genericAnnotation = genericAnnotation;
        }

        public GenericAnnotationInput getGenericAnnotation() {
            return genericAnnotation;
        }

        public AnnotationWrapperInput setGenericAnnotation(GenericAnnotationInput genericAnnotation) {
            this.genericAnnotation = genericAnnotation;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("genericAnnotation:");
            genericAnnotation.appendTo(_queryBuilder);

            _queryBuilder.append('}');
        }
    }

    public static class AttachmentInput implements Serializable {
        private AttachmentType type;

        private CardInput cardInput;

        public AttachmentInput(AttachmentType type) {
            this.type = type;
        }

        public AttachmentType getType() {
            return type;
        }

        public AttachmentInput setType(AttachmentType type) {
            this.type = type;
            return this;
        }

        public CardInput getCardInput() {
            return cardInput;
        }

        public AttachmentInput setCardInput(CardInput cardInput) {
            this.cardInput = cardInput;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("type:");
            _queryBuilder.append(type.toString());

            if (cardInput != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("cardInput:");
                cardInput.appendTo(_queryBuilder);
            }

            _queryBuilder.append('}');
        }
    }

    public enum AttachmentType {
        CARD,

        UNKNOWN_VALUE;

        public static AttachmentType fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "CARD": {
                    return CARD;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case CARD: {
                    return "CARD";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public static class ButtonInput implements Serializable {
        private String text;

        private String payload;

        private ButtonStyle style;

        public ButtonInput(String text, String payload, ButtonStyle style) {
            this.text = text;

            this.payload = payload;

            this.style = style;
        }

        public String getText() {
            return text;
        }

        public ButtonInput setText(String text) {
            this.text = text;
            return this;
        }

        public String getPayload() {
            return payload;
        }

        public ButtonInput setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public ButtonStyle getStyle() {
            return style;
        }

        public ButtonInput setStyle(ButtonStyle style) {
            this.style = style;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("text:");
            Query.appendQuotedString(_queryBuilder, text.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("payload:");
            Query.appendQuotedString(_queryBuilder, payload.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("style:");
            _queryBuilder.append(style.toString());

            _queryBuilder.append('}');
        }
    }

    public enum ButtonStyle {
        PRIMARY,

        SECONDARY,

        UNKNOWN_VALUE;

        public static ButtonStyle fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "PRIMARY": {
                    return PRIMARY;
                }

                case "SECONDARY": {
                    return SECONDARY;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case PRIMARY: {
                    return "PRIMARY";
                }

                case SECONDARY: {
                    return "SECONDARY";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public static class ButtonWrapperInput implements Serializable {
        private PostbackButtonInput postbackButton;

        public ButtonWrapperInput(PostbackButtonInput postbackButton) {
            this.postbackButton = postbackButton;
        }

        public PostbackButtonInput getPostbackButton() {
            return postbackButton;
        }

        public ButtonWrapperInput setPostbackButton(PostbackButtonInput postbackButton) {
            this.postbackButton = postbackButton;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("postbackButton:");
            postbackButton.appendTo(_queryBuilder);

            _queryBuilder.append('}');
        }
    }

    public static class CardInput implements Serializable {
        private CardType type;

        private InformationCardInput informationCardInput;

        public CardInput(CardType type) {
            this.type = type;
        }

        public CardType getType() {
            return type;
        }

        public CardInput setType(CardType type) {
            this.type = type;
            return this;
        }

        public InformationCardInput getInformationCardInput() {
            return informationCardInput;
        }

        public CardInput setInformationCardInput(InformationCardInput informationCardInput) {
            this.informationCardInput = informationCardInput;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("type:");
            _queryBuilder.append(type.toString());

            if (informationCardInput != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("informationCardInput:");
                informationCardInput.appendTo(_queryBuilder);
            }

            _queryBuilder.append('}');
        }
    }

    public enum CardType {
        INFORMATION,

        UNKNOWN_VALUE;

        public static CardType fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "INFORMATION": {
                    return INFORMATION;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case INFORMATION: {
                    return "INFORMATION";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface ConversationQueryDefinition {
        void define(ConversationQuery _queryBuilder);
    }

    public static class ConversationQuery extends Query<ConversationQuery> {
        ConversationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public ConversationQuery created() {
            startField("created");

            return this;
        }

        public ConversationQuery updated() {
            startField("updated");

            return this;
        }

        public class MessagesArguments extends Arguments {
            MessagesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MessagesArguments oldestTimestamp(String value) {
                if (value != null) {
                    startArgument("oldestTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MessagesArguments mostRecentTimestamp(String value) {
                if (value != null) {
                    startArgument("mostRecentTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MessagesArguments annotationType(String value) {
                if (value != null) {
                    startArgument("annotationType");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MessagesArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MessagesArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MessagesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MessagesArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MessagesArgumentsDefinition {
            void define(MessagesArguments args);
        }

        public ConversationQuery messages(MessageCollectionQueryDefinition queryDef) {
            return messages(args -> {}, queryDef);
        }

        public ConversationQuery messages(MessagesArgumentsDefinition argsDef, MessageCollectionQueryDefinition queryDef) {
            startField("messages");

            MessagesArguments args = new MessagesArguments(_queryBuilder);
            argsDef.define(args);
            MessagesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MessageCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public ConversationQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public ConversationQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Conversation extends AbstractResponse<Conversation> implements Node {
        public Conversation() {
        }

        public Conversation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "messages": {
                        MessageCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Conversation(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getMessages() != null) {
                children.addAll(getMessages().getNodes());
            }

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Conversation";
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Conversation setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Conversation setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public MessageCollection getMessages() {
            return (MessageCollection) get("messages");
        }

        public Conversation setMessages(MessageCollection arg) {
            optimisticData.put("messages", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Conversation setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Conversation setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "messages": return true;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public static class CreateSpaceInput implements Serializable {
        private String title;

        private List<String> members;

        public CreateSpaceInput(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public CreateSpaceInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getMembers() {
            return members;
        }

        public CreateSpaceInput setMembers(List<String> members) {
            this.members = members;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("title:");
            Query.appendQuotedString(_queryBuilder, title.toString());

            if (members != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : members) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1.toString());
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public static class CreateTargetedMessageInput implements Serializable {
        private String conversationId;

        private String targetDialogId;

        private String targetUserId;

        private List<AnnotationWrapperInput> annotations;

        private List<AttachmentInput> attachments;

        public CreateTargetedMessageInput(String conversationId, String targetDialogId, String targetUserId) {
            this.conversationId = conversationId;

            this.targetDialogId = targetDialogId;

            this.targetUserId = targetUserId;
        }

        public String getConversationId() {
            return conversationId;
        }

        public CreateTargetedMessageInput setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public String getTargetDialogId() {
            return targetDialogId;
        }

        public CreateTargetedMessageInput setTargetDialogId(String targetDialogId) {
            this.targetDialogId = targetDialogId;
            return this;
        }

        public String getTargetUserId() {
            return targetUserId;
        }

        public CreateTargetedMessageInput setTargetUserId(String targetUserId) {
            this.targetUserId = targetUserId;
            return this;
        }

        public List<AnnotationWrapperInput> getAnnotations() {
            return annotations;
        }

        public CreateTargetedMessageInput setAnnotations(List<AnnotationWrapperInput> annotations) {
            this.annotations = annotations;
            return this;
        }

        public List<AttachmentInput> getAttachments() {
            return attachments;
        }

        public CreateTargetedMessageInput setAttachments(List<AttachmentInput> attachments) {
            this.attachments = attachments;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("conversationId:");
            Query.appendQuotedString(_queryBuilder, conversationId.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetDialogId:");
            Query.appendQuotedString(_queryBuilder, targetDialogId.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetUserId:");
            Query.appendQuotedString(_queryBuilder, targetUserId.toString());

            if (annotations != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("annotations:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (AnnotationWrapperInput item1 : annotations) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    item1.appendTo(_queryBuilder);
                }
                _queryBuilder.append(']');
            }

            if (attachments != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("attachments:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (AttachmentInput item1 : attachments) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    item1.appendTo(_queryBuilder);
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public interface DeleteMutationQueryDefinition {
        void define(DeleteMutationQuery _queryBuilder);
    }

    public static class DeleteMutationQuery extends Query<DeleteMutationQuery> {
        DeleteMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public DeleteMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    public static class DeleteMutation extends AbstractResponse<DeleteMutation> {
        public DeleteMutation() {
        }

        public DeleteMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "successful": {
                        Boolean optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsBoolean(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            return children;
        }

        public String getGraphQlTypeName() {
            return "DeleteMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public DeleteMutation setSuccessful(Boolean arg) {
            optimisticData.put("successful", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public static class DeleteSpaceInput implements Serializable {
        private ID id;

        public DeleteSpaceInput(ID id) {
            this.id = id;
        }

        public ID getId() {
            return id;
        }

        public DeleteSpaceInput setId(ID id) {
            this.id = id;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append('}');
        }
    }

    public static class GenericAnnotationInput implements Serializable {
        private String text;

        private String title;

        private String color;

        private ActorInput actor;

        private List<ButtonWrapperInput> buttons;

        public GenericAnnotationInput(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public GenericAnnotationInput setText(String text) {
            this.text = text;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public GenericAnnotationInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getColor() {
            return color;
        }

        public GenericAnnotationInput setColor(String color) {
            this.color = color;
            return this;
        }

        public ActorInput getActor() {
            return actor;
        }

        public GenericAnnotationInput setActor(ActorInput actor) {
            this.actor = actor;
            return this;
        }

        public List<ButtonWrapperInput> getButtons() {
            return buttons;
        }

        public GenericAnnotationInput setButtons(List<ButtonWrapperInput> buttons) {
            this.buttons = buttons;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("text:");
            Query.appendQuotedString(_queryBuilder, text.toString());

            if (title != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                Query.appendQuotedString(_queryBuilder, title.toString());
            }

            if (color != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("color:");
                Query.appendQuotedString(_queryBuilder, color.toString());
            }

            if (actor != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("actor:");
                actor.appendTo(_queryBuilder);
            }

            if (buttons != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("buttons:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (ButtonWrapperInput item1 : buttons) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    item1.appendTo(_queryBuilder);
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public interface HighlightQueryDefinition {
        void define(HighlightQuery _queryBuilder);
    }

    public static class HighlightQuery extends Query<HighlightQuery> {
        HighlightQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public HighlightQuery field() {
            startField("field");

            return this;
        }

        public HighlightQuery highlighted() {
            startField("highlighted");

            return this;
        }
    }

    public static class Highlight extends AbstractResponse<Highlight> {
        public Highlight() {
        }

        public Highlight(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "field": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "highlighted": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            return children;
        }

        public String getGraphQlTypeName() {
            return "Highlight";
        }

        public String getField() {
            return (String) get("field");
        }

        public Highlight setField(String arg) {
            optimisticData.put("field", arg);
            return this;
        }

        public List<String> getHighlighted() {
            return (List<String>) get("highlighted");
        }

        public Highlight setHighlighted(List<String> arg) {
            optimisticData.put("highlighted", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "field": return false;

                case "highlighted": return false;

                default: return false;
            }
        }
    }

    public static class InformationCardInput implements Serializable {
        private String title;

        private String subtitle;

        private String text;

        private String date;

        private List<ButtonInput> buttons;

        public InformationCardInput(String title, String subtitle, String text, String date, List<ButtonInput> buttons) {
            this.title = title;

            this.subtitle = subtitle;

            this.text = text;

            this.date = date;

            this.buttons = buttons;
        }

        public String getTitle() {
            return title;
        }

        public InformationCardInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public InformationCardInput setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public String getText() {
            return text;
        }

        public InformationCardInput setText(String text) {
            this.text = text;
            return this;
        }

        public String getDate() {
            return date;
        }

        public InformationCardInput setDate(String date) {
            this.date = date;
            return this;
        }

        public List<ButtonInput> getButtons() {
            return buttons;
        }

        public InformationCardInput setButtons(List<ButtonInput> buttons) {
            this.buttons = buttons;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("title:");
            Query.appendQuotedString(_queryBuilder, title.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("subtitle:");
            Query.appendQuotedString(_queryBuilder, subtitle.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("text:");
            Query.appendQuotedString(_queryBuilder, text.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("date:");
            Query.appendQuotedString(_queryBuilder, date.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("buttons:");
            _queryBuilder.append('[');

            String listSeperator1 = "";
            for (ButtonInput item1 : buttons) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                item1.appendTo(_queryBuilder);
            }
            _queryBuilder.append(']');

            _queryBuilder.append('}');
        }
    }

    public enum MemberOperation {
        ADD,

        REMOVE,

        UNKNOWN_VALUE;

        public static MemberOperation fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "ADD": {
                    return ADD;
                }

                case "REMOVE": {
                    return REMOVE;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case ADD: {
                    return "ADD";
                }

                case REMOVE: {
                    return "REMOVE";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface MentionCollectionQueryDefinition {
        void define(MentionCollectionQuery _queryBuilder);
    }

    public static class MentionCollectionQuery extends Query<MentionCollectionQuery> {
        MentionCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MentionCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MentionCollectionQuery items(MentionedQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new MentionedQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MentionCollection extends AbstractResponse<MentionCollection> {
        public MentionCollection() {
        }

        public MentionCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Mentioned> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Mentioned optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Mentioned(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Mentioned elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MentionCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MentionCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Mentioned> getItems() {
            return (List<Mentioned>) get("items");
        }

        public MentionCollection setItems(List<Mentioned> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface MentionedQueryDefinition {
        void define(MentionedQuery _queryBuilder);
    }

    public static class MentionedQuery extends Query<MentionedQuery> {
        MentionedQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MentionedQuery updated() {
            startField("updated");

            return this;
        }

        public MentionedQuery updatedBy() {
            startField("updatedBy");

            return this;
        }

        public MentionedQuery space(SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MentionedQuery person(PersonQueryDefinition queryDef) {
            startField("person");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MentionedQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Mentioned extends AbstractResponse<Mentioned> {
        public Mentioned() {
        }

        public Mentioned(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "person": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            if (getPerson() != null) {
                children.addAll(getPerson().getNodes());
            }

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Mentioned";
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Mentioned setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public String getUpdatedBy() {
            return (String) get("updatedBy");
        }

        public Mentioned setUpdatedBy(String arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public Mentioned setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public Person getPerson() {
            return (Person) get("person");
        }

        public Mentioned setPerson(Person arg) {
            optimisticData.put("person", arg);
            return this;
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public Mentioned setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "updated": return false;

                case "updatedBy": return false;

                case "space": return true;

                case "person": return true;

                case "message": return true;

                default: return false;
            }
        }
    }

    public interface MessageQueryDefinition {
        void define(MessageQuery _queryBuilder);
    }

    public static class MessageQuery extends Query<MessageQuery> {
        MessageQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public MessageQuery content() {
            startField("content");

            return this;
        }

        public MessageQuery contentType() {
            startField("contentType");

            return this;
        }

        public MessageQuery annotations() {
            startField("annotations");

            return this;
        }

        public MessageQuery created() {
            startField("created");

            return this;
        }

        public MessageQuery updated() {
            startField("updated");

            return this;
        }

        public MessageQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Message extends AbstractResponse<Message> implements Node {
        public Message() {
        }

        public Message(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "content": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "contentType": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "annotations": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Message(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Message";
        }

        public String getContent() {
            return (String) get("content");
        }

        public Message setContent(String arg) {
            optimisticData.put("content", arg);
            return this;
        }

        public String getContentType() {
            return (String) get("contentType");
        }

        public Message setContentType(String arg) {
            optimisticData.put("contentType", arg);
            return this;
        }

        public List<String> getAnnotations() {
            return (List<String>) get("annotations");
        }

        public Message setAnnotations(List<String> arg) {
            optimisticData.put("annotations", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Message setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Message setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Message setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Message setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "content": return false;

                case "contentType": return false;

                case "annotations": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface MessageCollectionQueryDefinition {
        void define(MessageCollectionQuery _queryBuilder);
    }

    public static class MessageCollectionQuery extends Query<MessageCollectionQuery> {
        MessageCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageCollectionQuery items(MessageQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageCollection extends AbstractResponse<MessageCollection> {
        public MessageCollection() {
        }

        public MessageCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Message> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Message optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Message(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Message elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MessageCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Message> getItems() {
            return (List<Message>) get("items");
        }

        public MessageCollection setItems(List<Message> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public static class MessageFocusInput implements Serializable {
        private String lens;

        private String phrase;

        private String category;

        private List<String> actions;

        private Double confidence;

        private String payload;

        private Integer start;

        private Integer end;

        private Integer version;

        private Boolean hidden;

        public MessageFocusInput(String lens, String phrase) {
            this.lens = lens;

            this.phrase = phrase;
        }

        public String getLens() {
            return lens;
        }

        public MessageFocusInput setLens(String lens) {
            this.lens = lens;
            return this;
        }

        public String getPhrase() {
            return phrase;
        }

        public MessageFocusInput setPhrase(String phrase) {
            this.phrase = phrase;
            return this;
        }

        public String getCategory() {
            return category;
        }

        public MessageFocusInput setCategory(String category) {
            this.category = category;
            return this;
        }

        public List<String> getActions() {
            return actions;
        }

        public MessageFocusInput setActions(List<String> actions) {
            this.actions = actions;
            return this;
        }

        public Double getConfidence() {
            return confidence;
        }

        public MessageFocusInput setConfidence(Double confidence) {
            this.confidence = confidence;
            return this;
        }

        public String getPayload() {
            return payload;
        }

        public MessageFocusInput setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public Integer getStart() {
            return start;
        }

        public MessageFocusInput setStart(Integer start) {
            this.start = start;
            return this;
        }

        public Integer getEnd() {
            return end;
        }

        public MessageFocusInput setEnd(Integer end) {
            this.end = end;
            return this;
        }

        public Integer getVersion() {
            return version;
        }

        public MessageFocusInput setVersion(Integer version) {
            this.version = version;
            return this;
        }

        public Boolean getHidden() {
            return hidden;
        }

        public MessageFocusInput setHidden(Boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("lens:");
            Query.appendQuotedString(_queryBuilder, lens.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("phrase:");
            Query.appendQuotedString(_queryBuilder, phrase.toString());

            if (category != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("category:");
                Query.appendQuotedString(_queryBuilder, category.toString());
            }

            if (actions != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("actions:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : actions) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1.toString());
                }
                _queryBuilder.append(']');
            }

            if (confidence != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("confidence:");
                _queryBuilder.append(confidence);
            }

            if (payload != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("payload:");
                Query.appendQuotedString(_queryBuilder, payload.toString());
            }

            if (start != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("start:");
                _queryBuilder.append(start);
            }

            if (end != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("end:");
                _queryBuilder.append(end);
            }

            if (version != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("version:");
                _queryBuilder.append(version);
            }

            if (hidden != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("hidden:");
                _queryBuilder.append(hidden);
            }

            _queryBuilder.append('}');
        }
    }

    public interface MessageMutationQueryDefinition {
        void define(MessageMutationQuery _queryBuilder);
    }

    public static class MessageMutationQuery extends Query<MessageMutationQuery> {
        MessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageMutationQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageMutation extends AbstractResponse<MessageMutation> {
        public MessageMutation() {
        }

        public MessageMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageMutation";
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public MessageMutation setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "message": return true;

                default: return false;
            }
        }
    }

    public interface MessageSearchCollectionQueryDefinition {
        void define(MessageSearchCollectionQuery _queryBuilder);
    }

    public static class MessageSearchCollectionQuery extends Query<MessageSearchCollectionQuery> {
        MessageSearchCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageSearchCollectionQuery total() {
            startField("total");

            return this;
        }

        public MessageSearchCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageSearchCollectionQuery items(MessageSearchResultQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new MessageSearchResultQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageSearchCollection extends AbstractResponse<MessageSearchCollection> {
        public MessageSearchCollection() {
        }

        public MessageSearchCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "total": {
                        Integer optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsInteger(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<MessageSearchResult> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            MessageSearchResult optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new MessageSearchResult(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (MessageSearchResult elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageSearchCollection";
        }

        public Integer getTotal() {
            return (Integer) get("total");
        }

        public MessageSearchCollection setTotal(Integer arg) {
            optimisticData.put("total", arg);
            return this;
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MessageSearchCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<MessageSearchResult> getItems() {
            return (List<MessageSearchResult>) get("items");
        }

        public MessageSearchCollection setItems(List<MessageSearchResult> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "total": return false;

                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface MessageSearchResultQueryDefinition {
        void define(MessageSearchResultQuery _queryBuilder);
    }

    public static class MessageSearchResultQuery extends Query<MessageSearchResultQuery> {
        MessageSearchResultQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageSearchResultQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageSearchResultQuery highlights(HighlightQueryDefinition queryDef) {
            startField("highlights");

            _queryBuilder.append('{');
            queryDef.define(new HighlightQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageSearchResultQuery space(SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageSearchResult extends AbstractResponse<MessageSearchResult> {
        public MessageSearchResult() {
        }

        public MessageSearchResult(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "highlights": {
                        List<Highlight> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<Highlight> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                Highlight optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = new Highlight(jsonAsObject(element1, key));
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            if (getHighlights() != null) {
                for (Highlight elem: getHighlights()) {
                    children.addAll(elem.getNodes());
                }
            }

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageSearchResult";
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public MessageSearchResult setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public List<Highlight> getHighlights() {
            return (List<Highlight>) get("highlights");
        }

        public MessageSearchResult setHighlights(List<Highlight> arg) {
            optimisticData.put("highlights", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public MessageSearchResult setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "message": return true;

                case "highlights": return true;

                case "space": return true;

                default: return false;
            }
        }
    }

    public interface MutationRootQueryDefinition {
        void define(MutationRootQuery _queryBuilder);
    }

    public static class MutationRootQuery extends Query<MutationRootQuery> {
        MutationRootQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MutationRootQuery createSpace(CreateSpaceInput input, SpaceMutationQueryDefinition queryDef) {
            startField("createSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery updateSpace(UpdateSpaceInput input, SpaceMutationQueryDefinition queryDef) {
            startField("updateSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery addSpaceMembers(AddSpaceMembersInput input, SpaceMutationQueryDefinition queryDef) {
            startField("addSpaceMembers");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery removeSpaceMembers(RemoveSpaceMembersInput input, SpaceMutationQueryDefinition queryDef) {
            startField("removeSpaceMembers");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery deleteSpace(DeleteSpaceInput input, DeleteMutationQueryDefinition queryDef) {
            startField("deleteSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new DeleteMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery createTargetedMessage(CreateTargetedMessageInput input, TargetedMessageMutationQueryDefinition queryDef) {
            startField("createTargetedMessage");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new TargetedMessageMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery addMessageFocus(AddFocusInput input, MessageMutationQueryDefinition queryDef) {
            startField("addMessageFocus");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public String toString() {
            return _queryBuilder.toString();
        }
    }

    public static class MutationRoot extends AbstractResponse<MutationRoot> {
        public MutationRoot() {
        }

        public MutationRoot(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "createSpace": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updateSpace": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "addSpaceMembers": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "removeSpaceMembers": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "deleteSpace": {
                        DeleteMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new DeleteMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createTargetedMessage": {
                        TargetedMessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TargetedMessageMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "addMessageFocus": {
                        MessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getCreateSpace() != null) {
                children.addAll(getCreateSpace().getNodes());
            }

            if (getUpdateSpace() != null) {
                children.addAll(getUpdateSpace().getNodes());
            }

            if (getAddSpaceMembers() != null) {
                children.addAll(getAddSpaceMembers().getNodes());
            }

            if (getRemoveSpaceMembers() != null) {
                children.addAll(getRemoveSpaceMembers().getNodes());
            }

            if (getDeleteSpace() != null) {
                children.addAll(getDeleteSpace().getNodes());
            }

            if (getCreateTargetedMessage() != null) {
                children.addAll(getCreateTargetedMessage().getNodes());
            }

            if (getAddMessageFocus() != null) {
                children.addAll(getAddMessageFocus().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MutationRoot";
        }

        public SpaceMutation getCreateSpace() {
            return (SpaceMutation) get("createSpace");
        }

        public MutationRoot setCreateSpace(SpaceMutation arg) {
            optimisticData.put("createSpace", arg);
            return this;
        }

        public SpaceMutation getUpdateSpace() {
            return (SpaceMutation) get("updateSpace");
        }

        public MutationRoot setUpdateSpace(SpaceMutation arg) {
            optimisticData.put("updateSpace", arg);
            return this;
        }

        public SpaceMutation getAddSpaceMembers() {
            return (SpaceMutation) get("addSpaceMembers");
        }

        public MutationRoot setAddSpaceMembers(SpaceMutation arg) {
            optimisticData.put("addSpaceMembers", arg);
            return this;
        }

        public SpaceMutation getRemoveSpaceMembers() {
            return (SpaceMutation) get("removeSpaceMembers");
        }

        public MutationRoot setRemoveSpaceMembers(SpaceMutation arg) {
            optimisticData.put("removeSpaceMembers", arg);
            return this;
        }

        public DeleteMutation getDeleteSpace() {
            return (DeleteMutation) get("deleteSpace");
        }

        public MutationRoot setDeleteSpace(DeleteMutation arg) {
            optimisticData.put("deleteSpace", arg);
            return this;
        }

        public TargetedMessageMutation getCreateTargetedMessage() {
            return (TargetedMessageMutation) get("createTargetedMessage");
        }

        public MutationRoot setCreateTargetedMessage(TargetedMessageMutation arg) {
            optimisticData.put("createTargetedMessage", arg);
            return this;
        }

        public MessageMutation getAddMessageFocus() {
            return (MessageMutation) get("addMessageFocus");
        }

        public MutationRoot setAddMessageFocus(MessageMutation arg) {
            optimisticData.put("addMessageFocus", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "createSpace": return true;

                case "updateSpace": return true;

                case "addSpaceMembers": return true;

                case "removeSpaceMembers": return true;

                case "deleteSpace": return true;

                case "createTargetedMessage": return true;

                case "addMessageFocus": return true;

                default: return false;
            }
        }
    }

    public interface NodeQueryDefinition {
        void define(NodeQuery _queryBuilder);
    }

    public static class NodeQuery extends Query<NodeQuery> {
        NodeQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("__typename");
        }

        public NodeQuery id() {
            startField("id");

            return this;
        }

        public NodeQuery onConversation(ConversationQueryDefinition queryDef) {
            startInlineFragment("Conversation");
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onMessage(MessageQueryDefinition queryDef) {
            startInlineFragment("Message");
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onPerson(PersonQueryDefinition queryDef) {
            startInlineFragment("Person");
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onSpace(SpaceQueryDefinition queryDef) {
            startInlineFragment("Space");
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }
    }

    public interface Node {
        String getGraphQlTypeName();

        ID getId();
    }

    public static class UnknownNode extends AbstractResponse<UnknownNode> implements Node {
        public UnknownNode() {
        }

        public UnknownNode(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            return children;
        }

        public static Node create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "Conversation": {
                    return new Conversation(fields);
                }

                case "Message": {
                    return new Message(fields);
                }

                case "Person": {
                    return new Person(fields);
                }

                case "Space": {
                    return new Space(fields);
                }

                default: {
                    return new UnknownNode(fields);
                }
            }
        }

        public String getGraphQlTypeName() {
            return (String) get("__typename");
        }

        public ID getId() {
            return (ID) get("id");
        }

        public UnknownNode setId(ID arg) {
            optimisticData.put("id", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "id": return false;

                default: return false;
            }
        }
    }

    public interface PageInfoQueryDefinition {
        void define(PageInfoQuery _queryBuilder);
    }

    public static class PageInfoQuery extends Query<PageInfoQuery> {
        PageInfoQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public PageInfoQuery startCursor() {
            startField("startCursor");

            return this;
        }

        public PageInfoQuery endCursor() {
            startField("endCursor");

            return this;
        }

        public PageInfoQuery hasPreviousPage() {
            startField("hasPreviousPage");

            return this;
        }

        public PageInfoQuery hasNextPage() {
            startField("hasNextPage");

            return this;
        }
    }

    public static class PageInfo extends AbstractResponse<PageInfo> {
        public PageInfo() {
        }

        public PageInfo(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "startCursor": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "endCursor": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "hasPreviousPage": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "hasNextPage": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            return children;
        }

        public String getGraphQlTypeName() {
            return "PageInfo";
        }

        public String getStartCursor() {
            return (String) get("startCursor");
        }

        public PageInfo setStartCursor(String arg) {
            optimisticData.put("startCursor", arg);
            return this;
        }

        public String getEndCursor() {
            return (String) get("endCursor");
        }

        public PageInfo setEndCursor(String arg) {
            optimisticData.put("endCursor", arg);
            return this;
        }

        public Boolean getHasPreviousPage() {
            return (Boolean) get("hasPreviousPage");
        }

        public PageInfo setHasPreviousPage(Boolean arg) {
            optimisticData.put("hasPreviousPage", arg);
            return this;
        }

        public Boolean getHasNextPage() {
            return (Boolean) get("hasNextPage");
        }

        public PageInfo setHasNextPage(Boolean arg) {
            optimisticData.put("hasNextPage", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "startCursor": return false;

                case "endCursor": return false;

                case "hasPreviousPage": return false;

                case "hasNextPage": return false;

                default: return false;
            }
        }
    }

    public interface PersonQueryDefinition {
        void define(PersonQuery _queryBuilder);
    }

    public static class PersonQuery extends Query<PersonQuery> {
        PersonQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public PersonQuery displayName() {
            startField("displayName");

            return this;
        }

        public PersonQuery extId() {
            startField("extId");

            return this;
        }

        public PersonQuery email() {
            startField("email");

            return this;
        }

        public PersonQuery emailAddresses() {
            startField("emailAddresses");

            return this;
        }

        public PersonQuery photoUrl() {
            startField("photoUrl");

            return this;
        }

        public PersonQuery customerId() {
            startField("customerId");

            return this;
        }

        public PersonQuery type() {
            startField("type");

            return this;
        }

        public PersonQuery directMessageSpaceId() {
            startField("directMessageSpaceId");

            return this;
        }

        public PersonQuery created() {
            startField("created");

            return this;
        }

        public PersonQuery updated() {
            startField("updated");

            return this;
        }

        public PersonQuery presence() {
            startField("presence");

            return this;
        }

        public PersonQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public PersonQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Person extends AbstractResponse<Person> implements Node {
        public Person() {
        }

        public Person(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "displayName": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "extId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "email": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "emailAddresses": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "photoUrl": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "customerId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "type": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "directMessageSpaceId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "presence": {
                        PresenceStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PresenceStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Person(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Person";
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public Person setDisplayName(String arg) {
            optimisticData.put("displayName", arg);
            return this;
        }

        public String getExtId() {
            return (String) get("extId");
        }

        public Person setExtId(String arg) {
            optimisticData.put("extId", arg);
            return this;
        }

        public String getEmail() {
            return (String) get("email");
        }

        public Person setEmail(String arg) {
            optimisticData.put("email", arg);
            return this;
        }

        public List<String> getEmailAddresses() {
            return (List<String>) get("emailAddresses");
        }

        public Person setEmailAddresses(List<String> arg) {
            optimisticData.put("emailAddresses", arg);
            return this;
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public Person setPhotoUrl(String arg) {
            optimisticData.put("photoUrl", arg);
            return this;
        }

        public String getCustomerId() {
            return (String) get("customerId");
        }

        public Person setCustomerId(String arg) {
            optimisticData.put("customerId", arg);
            return this;
        }

        public String getType() {
            return (String) get("type");
        }

        public Person setType(String arg) {
            optimisticData.put("type", arg);
            return this;
        }

        public String getDirectMessageSpaceId() {
            return (String) get("directMessageSpaceId");
        }

        public Person setDirectMessageSpaceId(String arg) {
            optimisticData.put("directMessageSpaceId", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Person setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Person setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public PresenceStatus getPresence() {
            return (PresenceStatus) get("presence");
        }

        public Person setPresence(PresenceStatus arg) {
            optimisticData.put("presence", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Person setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Person setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "displayName": return false;

                case "extId": return false;

                case "email": return false;

                case "emailAddresses": return false;

                case "photoUrl": return false;

                case "customerId": return false;

                case "type": return false;

                case "directMessageSpaceId": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "presence": return false;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface PersonCollectionQueryDefinition {
        void define(PersonCollectionQuery _queryBuilder);
    }

    public static class PersonCollectionQuery extends Query<PersonCollectionQuery> {
        PersonCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public PersonCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public PersonCollectionQuery items(PersonQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class PersonCollection extends AbstractResponse<PersonCollection> {
        public PersonCollection() {
        }

        public PersonCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Person> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Person optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Person(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Person elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "PersonCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public PersonCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Person> getItems() {
            return (List<Person>) get("items");
        }

        public PersonCollection setItems(List<Person> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public static class PostbackButtonInput implements Serializable {
        private String id;

        private String title;

        private ButtonStyle style;

        public PostbackButtonInput(String id, String title, ButtonStyle style) {
            this.id = id;

            this.title = title;

            this.style = style;
        }

        public String getId() {
            return id;
        }

        public PostbackButtonInput setId(String id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public PostbackButtonInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public ButtonStyle getStyle() {
            return style;
        }

        public PostbackButtonInput setStyle(ButtonStyle style) {
            this.style = style;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("title:");
            Query.appendQuotedString(_queryBuilder, title.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("style:");
            _queryBuilder.append(style.toString());

            _queryBuilder.append('}');
        }
    }

    public enum PresenceStatus {
        OFFLINE,

        ONLINE,

        UNKNOWN_VALUE;

        public static PresenceStatus fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "offline": {
                    return OFFLINE;
                }

                case "online": {
                    return ONLINE;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case OFFLINE: {
                    return "offline";
                }

                case ONLINE: {
                    return "online";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface QueryRootQueryDefinition {
        void define(QueryRootQuery _queryBuilder);
    }

    public static class QueryRootQuery extends Query<QueryRootQuery> {
        QueryRootQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public QueryRootQuery me(PersonQueryDefinition queryDef) {
            startField("me");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class PeopleArguments extends Arguments {
            PeopleArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public PeopleArguments name(String value) {
                if (value != null) {
                    startArgument("name");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public PeopleArguments id(List<String> value) {
                if (value != null) {
                    startArgument("id");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public PeopleArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public PeopleArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public PeopleArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public PeopleArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface PeopleArgumentsDefinition {
            void define(PeopleArguments args);
        }

        public QueryRootQuery people(PersonCollectionQueryDefinition queryDef) {
            return people(args -> {}, queryDef);
        }

        public QueryRootQuery people(PeopleArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("people");

            PeopleArguments args = new PeopleArguments(_queryBuilder);
            argsDef.define(args);
            PeopleArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SpacesArguments extends Arguments {
            SpacesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public SpacesArguments updatedSince(String value) {
                if (value != null) {
                    startArgument("updatedSince");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SpacesArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SpacesArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SpacesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SpacesArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SpacesArgumentsDefinition {
            void define(SpacesArguments args);
        }

        public QueryRootQuery spaces(SpaceCollectionQueryDefinition queryDef) {
            return spaces(args -> {}, queryDef);
        }

        public QueryRootQuery spaces(SpacesArgumentsDefinition argsDef, SpaceCollectionQueryDefinition queryDef) {
            startField("spaces");

            SpacesArguments args = new SpacesArguments(_queryBuilder);
            argsDef.define(args);
            SpacesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new SpaceCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class MentionedArguments extends Arguments {
            MentionedArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MentionedArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MentionedArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MentionedArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MentionedArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MentionedArgumentsDefinition {
            void define(MentionedArguments args);
        }

        public QueryRootQuery mentioned(MentionCollectionQueryDefinition queryDef) {
            return mentioned(args -> {}, queryDef);
        }

        public QueryRootQuery mentioned(MentionedArgumentsDefinition argsDef, MentionCollectionQueryDefinition queryDef) {
            startField("mentioned");

            MentionedArguments args = new MentionedArguments(_queryBuilder);
            argsDef.define(args);
            MentionedArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MentionCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class PersonArguments extends Arguments {
            PersonArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public PersonArguments id(ID value) {
                if (value != null) {
                    startArgument("id");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public PersonArguments email(String value) {
                if (value != null) {
                    startArgument("email");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }
        }

        public interface PersonArgumentsDefinition {
            void define(PersonArguments args);
        }

        public QueryRootQuery person(PersonQueryDefinition queryDef) {
            return person(args -> {}, queryDef);
        }

        public QueryRootQuery person(PersonArgumentsDefinition argsDef, PersonQueryDefinition queryDef) {
            startField("person");

            PersonArguments args = new PersonArguments(_queryBuilder);
            argsDef.define(args);
            PersonArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery space(ID id, SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery conversation(ID id, ConversationQueryDefinition queryDef) {
            startField("conversation");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery message(ID id, MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SearchSpaceMembersArguments extends Arguments {
            SearchSpaceMembersArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public SearchSpaceMembersArguments name(String value) {
                if (value != null) {
                    startArgument("name");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchSpaceMembersArguments spaceId(String value) {
                if (value != null) {
                    startArgument("spaceId");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchSpaceMembersArguments memberType(String value) {
                if (value != null) {
                    startArgument("memberType");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchSpaceMembersArguments phonetic(Boolean value) {
                if (value != null) {
                    startArgument("phonetic");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchSpaceMembersArguments includeRequester(Boolean value) {
                if (value != null) {
                    startArgument("includeRequester");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchSpaceMembersArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchSpaceMembersArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchSpaceMembersArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchSpaceMembersArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }
        }

        public interface SearchSpaceMembersArgumentsDefinition {
            void define(SearchSpaceMembersArguments args);
        }

        public QueryRootQuery searchSpaceMembers(SpaceMembersSearchCollectionQueryDefinition queryDef) {
            return searchSpaceMembers(args -> {}, queryDef);
        }

        public QueryRootQuery searchSpaceMembers(SearchSpaceMembersArgumentsDefinition argsDef, SpaceMembersSearchCollectionQueryDefinition queryDef) {
            startField("searchSpaceMembers");

            SearchSpaceMembersArguments args = new SearchSpaceMembersArguments(_queryBuilder);
            argsDef.define(args);
            SearchSpaceMembersArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new SpaceMembersSearchCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SearchPeopleArguments extends Arguments {
            SearchPeopleArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, false);
            }

            public SearchPeopleArguments sources(List<String> value) {
                if (value != null) {
                    startArgument("sources");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public SearchPeopleArguments size(Integer value) {
                if (value != null) {
                    startArgument("size");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchPeopleArguments phonetic(Boolean value) {
                if (value != null) {
                    startArgument("phonetic");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchPeopleArguments includeRequester(Boolean value) {
                if (value != null) {
                    startArgument("includeRequester");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SearchPeopleArgumentsDefinition {
            void define(SearchPeopleArguments args);
        }

        public QueryRootQuery searchPeople(String name, PersonCollectionQueryDefinition queryDef) {
            return searchPeople(name, args -> {}, queryDef);
        }

        public QueryRootQuery searchPeople(String name, SearchPeopleArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("searchPeople");

            _queryBuilder.append("(name:");
            Query.appendQuotedString(_queryBuilder, name.toString());

            argsDef.define(new SearchPeopleArguments(_queryBuilder));

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SearchMessagesArguments extends Arguments {
            SearchMessagesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, false);
            }

            public SearchMessagesArguments locale(String value) {
                if (value != null) {
                    startArgument("locale");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchMessagesArguments searchFields(List<String> value) {
                if (value != null) {
                    startArgument("searchFields");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public SearchMessagesArguments from(List<String> value) {
                if (value != null) {
                    startArgument("from");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public SearchMessagesArguments in(List<String> value) {
                if (value != null) {
                    startArgument("in");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public SearchMessagesArguments dateFrom(String value) {
                if (value != null) {
                    startArgument("dateFrom");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchMessagesArguments dateTo(String value) {
                if (value != null) {
                    startArgument("dateTo");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchMessagesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchMessagesArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SearchMessagesArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchMessagesArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }
        }

        public interface SearchMessagesArgumentsDefinition {
            void define(SearchMessagesArguments args);
        }

        public QueryRootQuery searchMessages(String query, MessageSearchCollectionQueryDefinition queryDef) {
            return searchMessages(query, args -> {}, queryDef);
        }

        public QueryRootQuery searchMessages(String query, SearchMessagesArgumentsDefinition argsDef, MessageSearchCollectionQueryDefinition queryDef) {
            startField("searchMessages");

            _queryBuilder.append("(query:");
            Query.appendQuotedString(_queryBuilder, query.toString());

            argsDef.define(new SearchMessagesArguments(_queryBuilder));

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageSearchCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SearchSpacesArguments extends Arguments {
            SearchSpacesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, false);
            }

            public SearchSpacesArguments sortBy(String value) {
                if (value != null) {
                    startArgument("sortBy");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public SearchSpacesArguments size(Integer value) {
                if (value != null) {
                    startArgument("size");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SearchSpacesArgumentsDefinition {
            void define(SearchSpacesArguments args);
        }

        public QueryRootQuery searchSpaces(String title, SpaceCollectionQueryDefinition queryDef) {
            return searchSpaces(title, args -> {}, queryDef);
        }

        public QueryRootQuery searchSpaces(String title, SearchSpacesArgumentsDefinition argsDef, SpaceCollectionQueryDefinition queryDef) {
            startField("searchSpaces");

            _queryBuilder.append("(title:");
            Query.appendQuotedString(_queryBuilder, title.toString());

            argsDef.define(new SearchSpacesArguments(_queryBuilder));

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public String toString() {
            return _queryBuilder.toString();
        }
    }

    public static class QueryRoot extends AbstractResponse<QueryRoot> {
        public QueryRoot() {
        }

        public QueryRoot(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "me": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "people": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "spaces": {
                        SpaceCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "mentioned": {
                        MentionCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MentionCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "person": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "conversation": {
                        Conversation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Conversation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "searchSpaceMembers": {
                        SpaceMembersSearchCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMembersSearchCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "searchPeople": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "searchMessages": {
                        MessageSearchCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageSearchCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "searchSpaces": {
                        SpaceCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getMe() != null) {
                children.addAll(getMe().getNodes());
            }

            if (getPeople() != null) {
                children.addAll(getPeople().getNodes());
            }

            if (getSpaces() != null) {
                children.addAll(getSpaces().getNodes());
            }

            if (getMentioned() != null) {
                children.addAll(getMentioned().getNodes());
            }

            if (getPerson() != null) {
                children.addAll(getPerson().getNodes());
            }

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            if (getConversation() != null) {
                children.addAll(getConversation().getNodes());
            }

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            if (getSearchSpaceMembers() != null) {
                children.addAll(getSearchSpaceMembers().getNodes());
            }

            if (getSearchPeople() != null) {
                children.addAll(getSearchPeople().getNodes());
            }

            if (getSearchMessages() != null) {
                children.addAll(getSearchMessages().getNodes());
            }

            if (getSearchSpaces() != null) {
                children.addAll(getSearchSpaces().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "QueryRoot";
        }

        public Person getMe() {
            return (Person) get("me");
        }

        public QueryRoot setMe(Person arg) {
            optimisticData.put("me", arg);
            return this;
        }

        public PersonCollection getPeople() {
            return (PersonCollection) get("people");
        }

        public QueryRoot setPeople(PersonCollection arg) {
            optimisticData.put("people", arg);
            return this;
        }

        public SpaceCollection getSpaces() {
            return (SpaceCollection) get("spaces");
        }

        public QueryRoot setSpaces(SpaceCollection arg) {
            optimisticData.put("spaces", arg);
            return this;
        }

        public MentionCollection getMentioned() {
            return (MentionCollection) get("mentioned");
        }

        public QueryRoot setMentioned(MentionCollection arg) {
            optimisticData.put("mentioned", arg);
            return this;
        }

        public Person getPerson() {
            return (Person) get("person");
        }

        public QueryRoot setPerson(Person arg) {
            optimisticData.put("person", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public QueryRoot setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public QueryRoot setConversation(Conversation arg) {
            optimisticData.put("conversation", arg);
            return this;
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public QueryRoot setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public SpaceMembersSearchCollection getSearchSpaceMembers() {
            return (SpaceMembersSearchCollection) get("searchSpaceMembers");
        }

        public QueryRoot setSearchSpaceMembers(SpaceMembersSearchCollection arg) {
            optimisticData.put("searchSpaceMembers", arg);
            return this;
        }

        public PersonCollection getSearchPeople() {
            return (PersonCollection) get("searchPeople");
        }

        public QueryRoot setSearchPeople(PersonCollection arg) {
            optimisticData.put("searchPeople", arg);
            return this;
        }

        public MessageSearchCollection getSearchMessages() {
            return (MessageSearchCollection) get("searchMessages");
        }

        public QueryRoot setSearchMessages(MessageSearchCollection arg) {
            optimisticData.put("searchMessages", arg);
            return this;
        }

        public SpaceCollection getSearchSpaces() {
            return (SpaceCollection) get("searchSpaces");
        }

        public QueryRoot setSearchSpaces(SpaceCollection arg) {
            optimisticData.put("searchSpaces", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "me": return true;

                case "people": return true;

                case "spaces": return true;

                case "mentioned": return true;

                case "person": return true;

                case "space": return true;

                case "conversation": return true;

                case "message": return true;

                case "searchSpaceMembers": return true;

                case "searchPeople": return true;

                case "searchMessages": return true;

                case "searchSpaces": return true;

                default: return false;
            }
        }
    }

    public static class RemoveSpaceMembersInput implements Serializable {
        private ID spaceId;

        private List<String> members;

        public RemoveSpaceMembersInput(ID spaceId, List<String> members) {
            this.spaceId = spaceId;

            this.members = members;
        }

        public ID getSpaceId() {
            return spaceId;
        }

        public RemoveSpaceMembersInput setSpaceId(ID spaceId) {
            this.spaceId = spaceId;
            return this;
        }

        public List<String> getMembers() {
            return members;
        }

        public RemoveSpaceMembersInput setMembers(List<String> members) {
            this.members = members;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("spaceId:");
            Query.appendQuotedString(_queryBuilder, spaceId.toString());

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("members:");
            _queryBuilder.append('[');

            String listSeperator1 = "";
            for (String item1 : members) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                Query.appendQuotedString(_queryBuilder, item1.toString());
            }
            _queryBuilder.append(']');

            _queryBuilder.append('}');
        }
    }

    public interface SpaceQueryDefinition {
        void define(SpaceQuery _queryBuilder);
    }

    public static class SpaceQuery extends Query<SpaceQuery> {
        SpaceQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public SpaceQuery title() {
            startField("title");

            return this;
        }

        public SpaceQuery description() {
            startField("description");

            return this;
        }

        public SpaceQuery created() {
            startField("created");

            return this;
        }

        public SpaceQuery updated() {
            startField("updated");

            return this;
        }

        public SpaceQuery membersUpdated() {
            startField("membersUpdated");

            return this;
        }

        public SpaceQuery conversation(ConversationQueryDefinition queryDef) {
            startField("conversation");

            _queryBuilder.append('{');
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class MembersArguments extends Arguments {
            MembersArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MembersArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MembersArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MembersArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MembersArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MembersArgumentsDefinition {
            void define(MembersArguments args);
        }

        public SpaceQuery members(PersonCollectionQueryDefinition queryDef) {
            return members(args -> {}, queryDef);
        }

        public SpaceQuery members(MembersArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("members");

            MembersArguments args = new MembersArguments(_queryBuilder);
            argsDef.define(args);
            MembersArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Space extends AbstractResponse<Space> implements Node {
        public Space() {
        }

        public Space(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "title": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "description": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "membersUpdated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "conversation": {
                        Conversation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Conversation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "members": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Space(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getConversation() != null) {
                children.addAll(getConversation().getNodes());
            }

            if (getMembers() != null) {
                children.addAll(getMembers().getNodes());
            }

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Space";
        }

        public String getTitle() {
            return (String) get("title");
        }

        public Space setTitle(String arg) {
            optimisticData.put("title", arg);
            return this;
        }

        public String getDescription() {
            return (String) get("description");
        }

        public Space setDescription(String arg) {
            optimisticData.put("description", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Space setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Space setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public String getMembersUpdated() {
            return (String) get("membersUpdated");
        }

        public Space setMembersUpdated(String arg) {
            optimisticData.put("membersUpdated", arg);
            return this;
        }

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public Space setConversation(Conversation arg) {
            optimisticData.put("conversation", arg);
            return this;
        }

        public PersonCollection getMembers() {
            return (PersonCollection) get("members");
        }

        public Space setMembers(PersonCollection arg) {
            optimisticData.put("members", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Space setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Space setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "title": return false;

                case "description": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "membersUpdated": return false;

                case "conversation": return true;

                case "members": return true;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface SpaceCollectionQueryDefinition {
        void define(SpaceCollectionQuery _queryBuilder);
    }

    public static class SpaceCollectionQuery extends Query<SpaceCollectionQuery> {
        SpaceCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceCollectionQuery items(SpaceQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceCollection extends AbstractResponse<SpaceCollection> {
        public SpaceCollection() {
        }

        public SpaceCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Space> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Space optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Space(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Space elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public SpaceCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Space> getItems() {
            return (List<Space>) get("items");
        }

        public SpaceCollection setItems(List<Space> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface SpaceMemberQueryDefinition {
        void define(SpaceMemberQuery _queryBuilder);
    }

    public static class SpaceMemberQuery extends Query<SpaceMemberQuery> {
        SpaceMemberQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceMemberQuery permissions() {
            startField("permissions");

            return this;
        }

        public SpaceMemberQuery roles() {
            startField("roles");

            return this;
        }

        public SpaceMemberQuery displayName() {
            startField("displayName");

            return this;
        }

        public SpaceMemberQuery extId() {
            startField("extId");

            return this;
        }

        public SpaceMemberQuery email() {
            startField("email");

            return this;
        }

        public SpaceMemberQuery emailAddresses() {
            startField("emailAddresses");

            return this;
        }

        public SpaceMemberQuery photoUrl() {
            startField("photoUrl");

            return this;
        }

        public SpaceMemberQuery customerId() {
            startField("customerId");

            return this;
        }

        public SpaceMemberQuery type() {
            startField("type");

            return this;
        }

        public SpaceMemberQuery directMessageSpaceId() {
            startField("directMessageSpaceId");

            return this;
        }

        public SpaceMemberQuery created() {
            startField("created");

            return this;
        }

        public SpaceMemberQuery updated() {
            startField("updated");

            return this;
        }

        public SpaceMemberQuery id() {
            startField("id");

            return this;
        }

        public SpaceMemberQuery presence() {
            startField("presence");

            return this;
        }

        public SpaceMemberQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceMemberQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceMember extends AbstractResponse<SpaceMember> {
        public SpaceMember() {
        }

        public SpaceMember(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "permissions": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "roles": {
                        List<SpaceRole> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<SpaceRole> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                SpaceRole optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = SpaceRole.fromGraphQl(jsonAsString(element1, key));
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "displayName": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "extId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "email": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "emailAddresses": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "photoUrl": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "customerId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "type": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "directMessageSpaceId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "presence": {
                        PresenceStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PresenceStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceMember";
        }

        public List<String> getPermissions() {
            return (List<String>) get("permissions");
        }

        public SpaceMember setPermissions(List<String> arg) {
            optimisticData.put("permissions", arg);
            return this;
        }

        public List<SpaceRole> getRoles() {
            return (List<SpaceRole>) get("roles");
        }

        public SpaceMember setRoles(List<SpaceRole> arg) {
            optimisticData.put("roles", arg);
            return this;
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public SpaceMember setDisplayName(String arg) {
            optimisticData.put("displayName", arg);
            return this;
        }

        public String getExtId() {
            return (String) get("extId");
        }

        public SpaceMember setExtId(String arg) {
            optimisticData.put("extId", arg);
            return this;
        }

        public String getEmail() {
            return (String) get("email");
        }

        public SpaceMember setEmail(String arg) {
            optimisticData.put("email", arg);
            return this;
        }

        public List<String> getEmailAddresses() {
            return (List<String>) get("emailAddresses");
        }

        public SpaceMember setEmailAddresses(List<String> arg) {
            optimisticData.put("emailAddresses", arg);
            return this;
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public SpaceMember setPhotoUrl(String arg) {
            optimisticData.put("photoUrl", arg);
            return this;
        }

        public String getCustomerId() {
            return (String) get("customerId");
        }

        public SpaceMember setCustomerId(String arg) {
            optimisticData.put("customerId", arg);
            return this;
        }

        public String getType() {
            return (String) get("type");
        }

        public SpaceMember setType(String arg) {
            optimisticData.put("type", arg);
            return this;
        }

        public String getDirectMessageSpaceId() {
            return (String) get("directMessageSpaceId");
        }

        public SpaceMember setDirectMessageSpaceId(String arg) {
            optimisticData.put("directMessageSpaceId", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public SpaceMember setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public SpaceMember setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public SpaceMember setId(ID arg) {
            optimisticData.put("id", arg);
            return this;
        }

        public PresenceStatus getPresence() {
            return (PresenceStatus) get("presence");
        }

        public SpaceMember setPresence(PresenceStatus arg) {
            optimisticData.put("presence", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public SpaceMember setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public SpaceMember setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "permissions": return false;

                case "roles": return false;

                case "displayName": return false;

                case "extId": return false;

                case "email": return false;

                case "emailAddresses": return false;

                case "photoUrl": return false;

                case "customerId": return false;

                case "type": return false;

                case "directMessageSpaceId": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "presence": return false;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public static class SpaceMemberInput implements Serializable {
        private String id;

        private List<String> permissions;

        public SpaceMemberInput(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public SpaceMemberInput setId(String id) {
            this.id = id;
            return this;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public SpaceMemberInput setPermissions(List<String> permissions) {
            this.permissions = permissions;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            if (permissions != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("permissions:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : permissions) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1.toString());
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public interface SpaceMembersSearchCollectionQueryDefinition {
        void define(SpaceMembersSearchCollectionQuery _queryBuilder);
    }

    public static class SpaceMembersSearchCollectionQuery extends Query<SpaceMembersSearchCollectionQuery> {
        SpaceMembersSearchCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceMembersSearchCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceMembersSearchCollectionQuery items(SpaceMemberQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new SpaceMemberQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceMembersSearchCollection extends AbstractResponse<SpaceMembersSearchCollection> {
        public SpaceMembersSearchCollection() {
        }

        public SpaceMembersSearchCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<SpaceMember> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            SpaceMember optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new SpaceMember(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (SpaceMember elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceMembersSearchCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public SpaceMembersSearchCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<SpaceMember> getItems() {
            return (List<SpaceMember>) get("items");
        }

        public SpaceMembersSearchCollection setItems(List<SpaceMember> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface SpaceMutationQueryDefinition {
        void define(SpaceMutationQuery _queryBuilder);
    }

    public static class SpaceMutationQuery extends Query<SpaceMutationQuery> {
        SpaceMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceMutationQuery memberIdsChanged() {
            startField("memberIdsChanged");

            return this;
        }

        public SpaceMutationQuery space(SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceMutation extends AbstractResponse<SpaceMutation> {
        public SpaceMutation() {
        }

        public SpaceMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "memberIdsChanged": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceMutation";
        }

        public List<String> getMemberIdsChanged() {
            return (List<String>) get("memberIdsChanged");
        }

        public SpaceMutation setMemberIdsChanged(List<String> arg) {
            optimisticData.put("memberIdsChanged", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public SpaceMutation setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "memberIdsChanged": return false;

                case "space": return true;

                default: return false;
            }
        }
    }

    public enum SpaceRole {
        SPACE_GUEST,

        SPACE_MEMBER,

        SPACE_OWNER,

        UNKNOWN_VALUE;

        public static SpaceRole fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "SPACE_GUEST": {
                    return SPACE_GUEST;
                }

                case "SPACE_MEMBER": {
                    return SPACE_MEMBER;
                }

                case "SPACE_OWNER": {
                    return SPACE_OWNER;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case SPACE_GUEST: {
                    return "SPACE_GUEST";
                }

                case SPACE_MEMBER: {
                    return "SPACE_MEMBER";
                }

                case SPACE_OWNER: {
                    return "SPACE_OWNER";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface TargetedMessageMutationQueryDefinition {
        void define(TargetedMessageMutationQuery _queryBuilder);
    }

    public static class TargetedMessageMutationQuery extends Query<TargetedMessageMutationQuery> {
        TargetedMessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TargetedMessageMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    public static class TargetedMessageMutation extends AbstractResponse<TargetedMessageMutation> {
        public TargetedMessageMutation() {
        }

        public TargetedMessageMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "successful": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            return children;
        }

        public String getGraphQlTypeName() {
            return "TargetedMessageMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public TargetedMessageMutation setSuccessful(Boolean arg) {
            optimisticData.put("successful", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public static class UpdateSpaceInput implements Serializable {
        private String id;

        private String title;

        private List<String> members;

        private MemberOperation memberOperation;

        public UpdateSpaceInput(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public UpdateSpaceInput setId(String id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public UpdateSpaceInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getMembers() {
            return members;
        }

        public UpdateSpaceInput setMembers(List<String> members) {
            this.members = members;
            return this;
        }

        public MemberOperation getMemberOperation() {
            return memberOperation;
        }

        public UpdateSpaceInput setMemberOperation(MemberOperation memberOperation) {
            this.memberOperation = memberOperation;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            if (title != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                Query.appendQuotedString(_queryBuilder, title.toString());
            }

            if (members != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : members) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1.toString());
                }
                _queryBuilder.append(']');
            }

            if (memberOperation != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("memberOperation:");
                _queryBuilder.append(memberOperation.toString());
            }

            _queryBuilder.append('}');
        }
    }
}
