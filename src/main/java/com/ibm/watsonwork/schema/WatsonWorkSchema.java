package com.ibm.watsonwork.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.ID;
import com.shopify.graphql.support.Query;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;

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

        public MutationResponse() {
        }

        public MutationResponse(TopLevelResponse response) throws SchemaViolationError {
            this.response = response;
            this.data = response.getData() != null ? new MutationRoot(response.getData()) : null;
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
        private Input<String> name = Input.undefined();

        private Input<String> url = Input.undefined();

        private Input<String> avatar = Input.undefined();

        public String getName() {
            return name.getValue();
        }

        public Input<String> getNameInput() {
            return name;
        }

        public ActorInput setName(String name) {
            this.name = Input.optional(name);
            return this;
        }

        public ActorInput setNameInput(Input<String> name) {
            if (name == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.name = name;
            return this;
        }

        public String getUrl() {
            return url.getValue();
        }

        public Input<String> getUrlInput() {
            return url;
        }

        public ActorInput setUrl(String url) {
            this.url = Input.optional(url);
            return this;
        }

        public ActorInput setUrlInput(Input<String> url) {
            if (url == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.url = url;
            return this;
        }

        public String getAvatar() {
            return avatar.getValue();
        }

        public Input<String> getAvatarInput() {
            return avatar;
        }

        public ActorInput setAvatar(String avatar) {
            this.avatar = Input.optional(avatar);
            return this;
        }

        public ActorInput setAvatarInput(Input<String> avatar) {
            if (avatar == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.avatar = avatar;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            if (this.name.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("name:");
                if (name.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, name.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.url.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("url:");
                if (url.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, url.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.avatar.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("avatar:");
                if (avatar.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, avatar.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
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

    public interface AppUserQueryDefinition {
        void define(AppUserQuery _queryBuilder);
    }

    /**
    * A single third party application user object
    */
    public static class AppUserQuery extends Query<AppUserQuery> {
        AppUserQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public AppUserQuery photoUrl() {
            startField("photoUrl");

            return this;
        }

        public AppUserQuery displayName() {
            startField("displayName");

            return this;
        }

        public AppUserQuery url() {
            startField("url");

            return this;
        }
    }

    /**
    * A single third party application user object
    */
    public static class AppUser extends AbstractResponse<AppUser> {
        public AppUser() {
        }

        public AppUser(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "photoUrl": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
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

                    case "url": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
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

        public String getGraphQlTypeName() {
            return "AppUser";
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public AppUser setPhotoUrl(String arg) {
            optimisticData.put(getKey("photoUrl"), arg);
            return this;
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public AppUser setDisplayName(String arg) {
            optimisticData.put(getKey("displayName"), arg);
            return this;
        }

        public String getUrl() {
            return (String) get("url");
        }

        public AppUser setUrl(String arg) {
            optimisticData.put(getKey("url"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "photoUrl": return false;

                case "displayName": return false;

                case "url": return false;

                default: return false;
            }
        }
    }

    public static class AttachmentInput implements Serializable {
        private AttachmentType type;

        private Input<CardInput> cardInput = Input.undefined();

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
            return cardInput.getValue();
        }

        public Input<CardInput> getCardInputInput() {
            return cardInput;
        }

        public AttachmentInput setCardInput(CardInput cardInput) {
            this.cardInput = Input.optional(cardInput);
            return this;
        }

        public AttachmentInput setCardInputInput(Input<CardInput> cardInput) {
            if (cardInput == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.cardInput.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("cardInput:");
                if (cardInput.getValue() != null) {
                    cardInput.getValue().appendTo(_queryBuilder);
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    /**
    * An enum representing the types of Attachment available.
    */
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

    /**
    * [Beta] An enum listing the possible types for a button style
    */
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

        private Input<InformationCardInput> informationCardInput = Input.undefined();

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
            return informationCardInput.getValue();
        }

        public Input<InformationCardInput> getInformationCardInputInput() {
            return informationCardInput;
        }

        public CardInput setInformationCardInput(InformationCardInput informationCardInput) {
            this.informationCardInput = Input.optional(informationCardInput);
            return this;
        }

        public CardInput setInformationCardInputInput(Input<InformationCardInput> informationCardInput) {
            if (informationCardInput == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.informationCardInput.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("informationCardInput:");
                if (informationCardInput.getValue() != null) {
                    informationCardInput.getValue().appendTo(_queryBuilder);
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    /**
    * An enum representing the types of Card available.
    */
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

    /**
    * A single converstaion object
    */
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

        /**
        * Person that created the entity
        */
        public ConversationQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

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

        /**
        * The messages in a conversation
        */
        public ConversationQuery messages(MessageCollectionQueryDefinition queryDef) {
            return messages(args -> {}, queryDef);
        }

        /**
        * The messages in a conversation
        */
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

        /**
        * Person that updated the entity last
        */
        public ConversationQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class MomentsArguments extends Arguments {
            MomentsArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MomentsArguments predicted(Boolean value) {
                if (value != null) {
                    startArgument("predicted");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MomentsArguments oldestTimestamp(String value) {
                if (value != null) {
                    startArgument("oldestTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments mostRecentTimestamp(String value) {
                if (value != null) {
                    startArgument("mostRecentTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MomentsArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MomentsArgumentsDefinition {
            void define(MomentsArguments args);
        }

        /**
        * The moments in a conversation
        */
        public ConversationQuery moments(MomentCollectionQueryDefinition queryDef) {
            return moments(args -> {}, queryDef);
        }

        /**
        * The moments in a conversation
        */
        public ConversationQuery moments(MomentsArgumentsDefinition argsDef, MomentCollectionQueryDefinition queryDef) {
            startField("moments");

            MomentsArguments args = new MomentsArguments(_queryBuilder);
            argsDef.define(args);
            MomentsArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MomentCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single converstaion object
    */
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
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

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "moments": {
                        MomentCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MomentCollection(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "Conversation";
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Conversation setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Conversation setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Conversation setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        /**
        * The messages in a conversation
        */

        public MessageCollection getMessages() {
            return (MessageCollection) get("messages");
        }

        public Conversation setMessages(MessageCollection arg) {
            optimisticData.put(getKey("messages"), arg);
            return this;
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Conversation setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        /**
        * The moments in a conversation
        */

        public MomentCollection getMoments() {
            return (MomentCollection) get("moments");
        }

        public Conversation setMoments(MomentCollection arg) {
            optimisticData.put(getKey("moments"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "id": return false;

                case "messages": return true;

                case "updatedBy": return true;

                case "moments": return true;

                default: return false;
            }
        }
    }

    public static class CreateMessageInput implements Serializable {
        private String conversationId;

        private Input<String> content = Input.undefined();

        private Input<List<AnnotationWrapperInput>> annotations = Input.undefined();

        public CreateMessageInput(String conversationId) {
            this.conversationId = conversationId;
        }

        public String getConversationId() {
            return conversationId;
        }

        public CreateMessageInput setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public String getContent() {
            return content.getValue();
        }

        public Input<String> getContentInput() {
            return content;
        }

        public CreateMessageInput setContent(String content) {
            this.content = Input.optional(content);
            return this;
        }

        public CreateMessageInput setContentInput(Input<String> content) {
            if (content == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.content = content;
            return this;
        }

        public List<AnnotationWrapperInput> getAnnotations() {
            return annotations.getValue();
        }

        public Input<List<AnnotationWrapperInput>> getAnnotationsInput() {
            return annotations;
        }

        public CreateMessageInput setAnnotations(List<AnnotationWrapperInput> annotations) {
            this.annotations = Input.optional(annotations);
            return this;
        }

        public CreateMessageInput setAnnotationsInput(Input<List<AnnotationWrapperInput>> annotations) {
            if (annotations == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.annotations = annotations;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("conversationId:");
            Query.appendQuotedString(_queryBuilder, conversationId.toString());

            if (this.content.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("content:");
                if (content.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, content.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.annotations.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("annotations:");
                if (annotations.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (AnnotationWrapperInput item1 : annotations.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        item1.appendTo(_queryBuilder);
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    public static class CreateSpaceInput implements Serializable {
        private String title;

        private Input<List<String>> members = Input.undefined();

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
            return members.getValue();
        }

        public Input<List<String>> getMembersInput() {
            return members;
        }

        public CreateSpaceInput setMembers(List<String> members) {
            this.members = Input.optional(members);
            return this;
        }

        public CreateSpaceInput setMembersInput(Input<List<String>> members) {
            if (members == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.members.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                if (members.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : members.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    public static class CreateTargetedMessageInput implements Serializable {
        private String conversationId;

        private String targetDialogId;

        private String targetUserId;

        private Input<List<AnnotationWrapperInput>> annotations = Input.undefined();

        private Input<List<AttachmentInput>> attachments = Input.undefined();

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
            return annotations.getValue();
        }

        public Input<List<AnnotationWrapperInput>> getAnnotationsInput() {
            return annotations;
        }

        public CreateTargetedMessageInput setAnnotations(List<AnnotationWrapperInput> annotations) {
            this.annotations = Input.optional(annotations);
            return this;
        }

        public CreateTargetedMessageInput setAnnotationsInput(Input<List<AnnotationWrapperInput>> annotations) {
            if (annotations == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.annotations = annotations;
            return this;
        }

        public List<AttachmentInput> getAttachments() {
            return attachments.getValue();
        }

        public Input<List<AttachmentInput>> getAttachmentsInput() {
            return attachments;
        }

        public CreateTargetedMessageInput setAttachments(List<AttachmentInput> attachments) {
            this.attachments = Input.optional(attachments);
            return this;
        }

        public CreateTargetedMessageInput setAttachmentsInput(Input<List<AttachmentInput>> attachments) {
            if (attachments == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.annotations.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("annotations:");
                if (annotations.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (AnnotationWrapperInput item1 : annotations.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        item1.appendTo(_queryBuilder);
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.attachments.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("attachments:");
                if (attachments.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (AttachmentInput item1 : attachments.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        item1.appendTo(_queryBuilder);
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
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

        public String getGraphQlTypeName() {
            return "DeleteMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public DeleteMutation setSuccessful(Boolean arg) {
            optimisticData.put(getKey("successful"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    public interface EntityQueryDefinition {
        void define(EntityQuery _queryBuilder);
    }

    /**
    * A single entity object
    */
    public static class EntityQuery extends Query<EntityQuery> {
        EntityQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public EntityQuery count() {
            startField("count");

            return this;
        }

        public EntityQuery label() {
            startField("label");

            return this;
        }

        public EntityQuery score() {
            startField("score");

            return this;
        }
    }

    /**
    * A single entity object
    */
    public static class Entity extends AbstractResponse<Entity> implements SummaryPhrase {
        public Entity() {
        }

        public Entity(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "count": {
                        Integer optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsInteger(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
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

        public String getGraphQlTypeName() {
            return "Entity";
        }

        public Integer getCount() {
            return (Integer) get("count");
        }

        public Entity setCount(Integer arg) {
            optimisticData.put(getKey("count"), arg);
            return this;
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Entity setLabel(String arg) {
            optimisticData.put(getKey("label"), arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Entity setScore(Double arg) {
            optimisticData.put(getKey("score"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "count": return false;

                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public static class GenericAnnotationInput implements Serializable {
        private String text;

        private Input<String> title = Input.undefined();

        private Input<String> color = Input.undefined();

        private Input<ActorInput> actor = Input.undefined();

        private Input<List<ButtonWrapperInput>> buttons = Input.undefined();

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
            return title.getValue();
        }

        public Input<String> getTitleInput() {
            return title;
        }

        public GenericAnnotationInput setTitle(String title) {
            this.title = Input.optional(title);
            return this;
        }

        public GenericAnnotationInput setTitleInput(Input<String> title) {
            if (title == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.title = title;
            return this;
        }

        public String getColor() {
            return color.getValue();
        }

        public Input<String> getColorInput() {
            return color;
        }

        public GenericAnnotationInput setColor(String color) {
            this.color = Input.optional(color);
            return this;
        }

        public GenericAnnotationInput setColorInput(Input<String> color) {
            if (color == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.color = color;
            return this;
        }

        public ActorInput getActor() {
            return actor.getValue();
        }

        public Input<ActorInput> getActorInput() {
            return actor;
        }

        public GenericAnnotationInput setActor(ActorInput actor) {
            this.actor = Input.optional(actor);
            return this;
        }

        public GenericAnnotationInput setActorInput(Input<ActorInput> actor) {
            if (actor == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.actor = actor;
            return this;
        }

        public List<ButtonWrapperInput> getButtons() {
            return buttons.getValue();
        }

        public Input<List<ButtonWrapperInput>> getButtonsInput() {
            return buttons;
        }

        public GenericAnnotationInput setButtons(List<ButtonWrapperInput> buttons) {
            this.buttons = Input.optional(buttons);
            return this;
        }

        public GenericAnnotationInput setButtonsInput(Input<List<ButtonWrapperInput>> buttons) {
            if (buttons == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.title.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                if (title.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, title.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.color.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("color:");
                if (color.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, color.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.actor.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("actor:");
                if (actor.getValue() != null) {
                    actor.getValue().appendTo(_queryBuilder);
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.buttons.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("buttons:");
                if (buttons.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (ButtonWrapperInput item1 : buttons.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        item1.appendTo(_queryBuilder);
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
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

        public String getGraphQlTypeName() {
            return "Highlight";
        }

        public String getField() {
            return (String) get("field");
        }

        public Highlight setField(String arg) {
            optimisticData.put(getKey("field"), arg);
            return this;
        }

        public List<String> getHighlighted() {
            return (List<String>) get("highlighted");
        }

        public Highlight setHighlighted(List<String> arg) {
            optimisticData.put(getKey("highlighted"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    public interface KeywordQueryDefinition {
        void define(KeywordQuery _queryBuilder);
    }

    /**
    * A single keyword object
    */
    public static class KeywordQuery extends Query<KeywordQuery> {
        KeywordQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public KeywordQuery label() {
            startField("label");

            return this;
        }

        public KeywordQuery score() {
            startField("score");

            return this;
        }
    }

    /**
    * A single keyword object
    */
    public static class Keyword extends AbstractResponse<Keyword> implements SummaryPhrase {
        public Keyword() {
        }

        public Keyword(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
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

        public String getGraphQlTypeName() {
            return "Keyword";
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Keyword setLabel(String arg) {
            optimisticData.put(getKey("label"), arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Keyword setScore(Double arg) {
            optimisticData.put(getKey("score"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "label": return false;

                case "score": return false;

                default: return false;
            }
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

        public String getGraphQlTypeName() {
            return "MentionCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MentionCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<Mentioned> getItems() {
            return (List<Mentioned>) get("items");
        }

        public MentionCollection setItems(List<Mentioned> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

        public MentionedQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

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

                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "Mentioned";
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Mentioned setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        public String getUpdatedBy() {
            return (String) get("updatedBy");
        }

        public Mentioned setUpdatedBy(String arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public Mentioned setMessage(Message arg) {
            optimisticData.put(getKey("message"), arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public Mentioned setSpace(Space arg) {
            optimisticData.put(getKey("space"), arg);
            return this;
        }

        public Person getPerson() {
            return (Person) get("person");
        }

        public Mentioned setPerson(Person arg) {
            optimisticData.put(getKey("person"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "updated": return false;

                case "updatedBy": return false;

                case "message": return true;

                case "space": return true;

                case "person": return true;

                default: return false;
            }
        }
    }

    public interface MessageQueryDefinition {
        void define(MessageQuery _queryBuilder);
    }

    /**
    * A single message object
    */
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

        /**
        * Person that created the entity
        */
        public MessageQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Person that updated the entity last
        */
        public MessageQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single message object
    */
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

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

        public String getGraphQlTypeName() {
            return "Message";
        }

        public String getContent() {
            return (String) get("content");
        }

        public Message setContent(String arg) {
            optimisticData.put(getKey("content"), arg);
            return this;
        }

        public String getContentType() {
            return (String) get("contentType");
        }

        public Message setContentType(String arg) {
            optimisticData.put(getKey("contentType"), arg);
            return this;
        }

        public List<String> getAnnotations() {
            return (List<String>) get("annotations");
        }

        public Message setAnnotations(List<String> arg) {
            optimisticData.put(getKey("annotations"), arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Message setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Message setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Message setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Message setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "content": return false;

                case "contentType": return false;

                case "annotations": return false;

                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "id": return false;

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

        public String getGraphQlTypeName() {
            return "MessageCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MessageCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<Message> getItems() {
            return (List<Message>) get("items");
        }

        public MessageCollection setItems(List<Message> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public static class MessageFocusInput implements Serializable {
        private String lens;

        private String phrase;

        private Input<String> category = Input.undefined();

        private Input<List<String>> actions = Input.undefined();

        private Input<Double> confidence = Input.undefined();

        private Input<String> payload = Input.undefined();

        private Input<Integer> start = Input.undefined();

        private Input<Integer> end = Input.undefined();

        private Input<Integer> version = Input.undefined();

        private Input<Boolean> hidden = Input.undefined();

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
            return category.getValue();
        }

        public Input<String> getCategoryInput() {
            return category;
        }

        public MessageFocusInput setCategory(String category) {
            this.category = Input.optional(category);
            return this;
        }

        public MessageFocusInput setCategoryInput(Input<String> category) {
            if (category == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.category = category;
            return this;
        }

        public List<String> getActions() {
            return actions.getValue();
        }

        public Input<List<String>> getActionsInput() {
            return actions;
        }

        public MessageFocusInput setActions(List<String> actions) {
            this.actions = Input.optional(actions);
            return this;
        }

        public MessageFocusInput setActionsInput(Input<List<String>> actions) {
            if (actions == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.actions = actions;
            return this;
        }

        public Double getConfidence() {
            return confidence.getValue();
        }

        public Input<Double> getConfidenceInput() {
            return confidence;
        }

        public MessageFocusInput setConfidence(Double confidence) {
            this.confidence = Input.optional(confidence);
            return this;
        }

        public MessageFocusInput setConfidenceInput(Input<Double> confidence) {
            if (confidence == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.confidence = confidence;
            return this;
        }

        public String getPayload() {
            return payload.getValue();
        }

        public Input<String> getPayloadInput() {
            return payload;
        }

        public MessageFocusInput setPayload(String payload) {
            this.payload = Input.optional(payload);
            return this;
        }

        public MessageFocusInput setPayloadInput(Input<String> payload) {
            if (payload == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.payload = payload;
            return this;
        }

        public Integer getStart() {
            return start.getValue();
        }

        public Input<Integer> getStartInput() {
            return start;
        }

        public MessageFocusInput setStart(Integer start) {
            this.start = Input.optional(start);
            return this;
        }

        public MessageFocusInput setStartInput(Input<Integer> start) {
            if (start == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.start = start;
            return this;
        }

        public Integer getEnd() {
            return end.getValue();
        }

        public Input<Integer> getEndInput() {
            return end;
        }

        public MessageFocusInput setEnd(Integer end) {
            this.end = Input.optional(end);
            return this;
        }

        public MessageFocusInput setEndInput(Input<Integer> end) {
            if (end == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.end = end;
            return this;
        }

        public Integer getVersion() {
            return version.getValue();
        }

        public Input<Integer> getVersionInput() {
            return version;
        }

        public MessageFocusInput setVersion(Integer version) {
            this.version = Input.optional(version);
            return this;
        }

        public MessageFocusInput setVersionInput(Input<Integer> version) {
            if (version == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.version = version;
            return this;
        }

        public Boolean getHidden() {
            return hidden.getValue();
        }

        public Input<Boolean> getHiddenInput() {
            return hidden;
        }

        public MessageFocusInput setHidden(Boolean hidden) {
            this.hidden = Input.optional(hidden);
            return this;
        }

        public MessageFocusInput setHiddenInput(Input<Boolean> hidden) {
            if (hidden == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.category.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("category:");
                if (category.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, category.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.actions.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("actions:");
                if (actions.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : actions.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.confidence.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("confidence:");
                if (confidence.getValue() != null) {
                    _queryBuilder.append(confidence.getValue());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.payload.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("payload:");
                if (payload.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, payload.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.start.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("start:");
                if (start.getValue() != null) {
                    _queryBuilder.append(start.getValue());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.end.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("end:");
                if (end.getValue() != null) {
                    _queryBuilder.append(end.getValue());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.version.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("version:");
                if (version.getValue() != null) {
                    _queryBuilder.append(version.getValue());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.hidden.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("hidden:");
                if (hidden.getValue() != null) {
                    _queryBuilder.append(hidden.getValue());
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    public interface MessageMutationQueryDefinition {
        void define(MessageMutationQuery _queryBuilder);
    }

    /**
    * A mutation object for a message object
    */
    public static class MessageMutationQuery extends Query<MessageMutationQuery> {
        MessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        /**
        * Retrieve a message by its id
        */
        public MessageMutationQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A mutation object for a message object
    */
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

        public String getGraphQlTypeName() {
            return "MessageMutation";
        }

        /**
        * Retrieve a message by its id
        */

        public Message getMessage() {
            return (Message) get("message");
        }

        public MessageMutation setMessage(Message arg) {
            optimisticData.put(getKey("message"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

        public String getGraphQlTypeName() {
            return "MessageSearchCollection";
        }

        public Integer getTotal() {
            return (Integer) get("total");
        }

        public MessageSearchCollection setTotal(Integer arg) {
            optimisticData.put(getKey("total"), arg);
            return this;
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MessageSearchCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<MessageSearchResult> getItems() {
            return (List<MessageSearchResult>) get("items");
        }

        public MessageSearchCollection setItems(List<MessageSearchResult> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    /**
    * An object containing a message returned by a search plus highlights and space information
    */
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

    /**
    * An object containing a message returned by a search plus highlights and space information
    */
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

        public String getGraphQlTypeName() {
            return "MessageSearchResult";
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public MessageSearchResult setMessage(Message arg) {
            optimisticData.put(getKey("message"), arg);
            return this;
        }

        public List<Highlight> getHighlights() {
            return (List<Highlight>) get("highlights");
        }

        public MessageSearchResult setHighlights(List<Highlight> arg) {
            optimisticData.put(getKey("highlights"), arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public MessageSearchResult setSpace(Space arg) {
            optimisticData.put(getKey("space"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "message": return true;

                case "highlights": return true;

                case "space": return true;

                default: return false;
            }
        }
    }

    public interface MomentQueryDefinition {
        void define(MomentQuery _queryBuilder);
    }

    /**
    * A single moment object
    */
    public static class MomentQuery extends Query<MomentQuery> {
        MomentQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public MomentQuery live() {
            startField("live");

            return this;
        }

        public MomentQuery startTime() {
            startField("startTime");

            return this;
        }

        public MomentQuery endTime() {
            startField("endTime");

            return this;
        }

        /**
        * The priority of a moment
        */
        public MomentQuery priority(UserPriorityStatusQueryDefinition queryDef) {
            startField("priority");

            _queryBuilder.append('{');
            queryDef.define(new UserPriorityStatusQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MomentQuery created() {
            startField("created");

            return this;
        }

        public MomentQuery updated() {
            startField("updated");

            return this;
        }

        /**
        * Person that created the entity
        */
        public MomentQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * The space of a moment
        */
        public MomentQuery space(SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class MentionedArguments extends Arguments {
            MentionedArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MentionedArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MentionedArgumentsDefinition {
            void define(MentionedArguments args);
        }

        /**
        * Retrieve a list of the calling user's mentions in a moment
        */
        public MomentQuery mentioned(MentionedQueryDefinition queryDef) {
            return mentioned(args -> {}, queryDef);
        }

        /**
        * Retrieve a list of the calling user's mentions in a moment
        */
        public MomentQuery mentioned(MentionedArgumentsDefinition argsDef, MentionedQueryDefinition queryDef) {
            startField("mentioned");

            MentionedArguments args = new MentionedArguments(_queryBuilder);
            argsDef.define(args);
            MentionedArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MentionedQuery(_queryBuilder));
            _queryBuilder.append('}');

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

        /**
        * The messages in a moment
        */
        public MomentQuery messages(MessageCollectionQueryDefinition queryDef) {
            return messages(args -> {}, queryDef);
        }

        /**
        * The messages in a moment
        */
        public MomentQuery messages(MessagesArgumentsDefinition argsDef, MessageCollectionQueryDefinition queryDef) {
            startField("messages");

            MessagesArguments args = new MessagesArguments(_queryBuilder);
            argsDef.define(args);
            MessagesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MessageCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Person that updated the entity last
        */
        public MomentQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * The key message of a moment
        */
        public MomentQuery keyMessage(MessageQueryDefinition queryDef) {
            startField("keyMessage");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SummaryPhrasesArguments extends Arguments {
            SummaryPhrasesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public SummaryPhrasesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SummaryPhrasesArgumentsDefinition {
            void define(SummaryPhrasesArguments args);
        }

        /**
        * The summary phrases in a moment
        */
        public MomentQuery summaryPhrases(SummaryPhraseQueryDefinition queryDef) {
            return summaryPhrases(args -> {}, queryDef);
        }

        /**
        * The summary phrases in a moment
        */
        public MomentQuery summaryPhrases(SummaryPhrasesArgumentsDefinition argsDef, SummaryPhraseQueryDefinition queryDef) {
            startField("summaryPhrases");

            SummaryPhrasesArguments args = new SummaryPhrasesArguments(_queryBuilder);
            argsDef.define(args);
            SummaryPhrasesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new SummaryPhraseQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class ParticipantsArguments extends Arguments {
            ParticipantsArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public ParticipantsArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface ParticipantsArgumentsDefinition {
            void define(ParticipantsArguments args);
        }

        /**
        * The participants in a moment
        */
        public MomentQuery participants(MomentParticipantQueryDefinition queryDef) {
            return participants(args -> {}, queryDef);
        }

        /**
        * The participants in a moment
        */
        public MomentQuery participants(ParticipantsArgumentsDefinition argsDef, MomentParticipantQueryDefinition queryDef) {
            startField("participants");

            ParticipantsArguments args = new ParticipantsArguments(_queryBuilder);
            argsDef.define(args);
            ParticipantsArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MomentParticipantQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single moment object
    */
    public static class Moment extends AbstractResponse<Moment> implements Node {
        public Moment() {
        }

        public Moment(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "live": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "startTime": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "endTime": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "priority": {
                        UserPriorityStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new UserPriorityStatus(jsonAsObject(field.getValue(), key));
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

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

                    case "mentioned": {
                        List<Mentioned> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<Mentioned> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                Mentioned optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = new Mentioned(jsonAsObject(element1, key));
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

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

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "keyMessage": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "summaryPhrases": {
                        List<SummaryPhrase> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<SummaryPhrase> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                SummaryPhrase optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = UnknownSummaryPhrase.create(jsonAsObject(element1, key));
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "participants": {
                        List<MomentParticipant> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<MomentParticipant> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                MomentParticipant optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = new MomentParticipant(jsonAsObject(element1, key));
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

        public Moment(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public String getGraphQlTypeName() {
            return "Moment";
        }

        public Boolean getLive() {
            return (Boolean) get("live");
        }

        public Moment setLive(Boolean arg) {
            optimisticData.put(getKey("live"), arg);
            return this;
        }

        public String getStartTime() {
            return (String) get("startTime");
        }

        public Moment setStartTime(String arg) {
            optimisticData.put(getKey("startTime"), arg);
            return this;
        }

        public String getEndTime() {
            return (String) get("endTime");
        }

        public Moment setEndTime(String arg) {
            optimisticData.put(getKey("endTime"), arg);
            return this;
        }

        /**
        * The priority of a moment
        */

        public UserPriorityStatus getPriority() {
            return (UserPriorityStatus) get("priority");
        }

        public Moment setPriority(UserPriorityStatus arg) {
            optimisticData.put(getKey("priority"), arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Moment setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Moment setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Moment setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        /**
        * The space of a moment
        */

        public Space getSpace() {
            return (Space) get("space");
        }

        public Moment setSpace(Space arg) {
            optimisticData.put(getKey("space"), arg);
            return this;
        }

        /**
        * Retrieve a list of the calling user's mentions in a moment
        */

        public List<Mentioned> getMentioned() {
            return (List<Mentioned>) get("mentioned");
        }

        public Moment setMentioned(List<Mentioned> arg) {
            optimisticData.put(getKey("mentioned"), arg);
            return this;
        }

        /**
        * The messages in a moment
        */

        public MessageCollection getMessages() {
            return (MessageCollection) get("messages");
        }

        public Moment setMessages(MessageCollection arg) {
            optimisticData.put(getKey("messages"), arg);
            return this;
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Moment setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        /**
        * The key message of a moment
        */

        public Message getKeyMessage() {
            return (Message) get("keyMessage");
        }

        public Moment setKeyMessage(Message arg) {
            optimisticData.put(getKey("keyMessage"), arg);
            return this;
        }

        /**
        * The summary phrases in a moment
        */

        public List<SummaryPhrase> getSummaryPhrases() {
            return (List<SummaryPhrase>) get("summaryPhrases");
        }

        public Moment setSummaryPhrases(List<SummaryPhrase> arg) {
            optimisticData.put(getKey("summaryPhrases"), arg);
            return this;
        }

        /**
        * The participants in a moment
        */

        public List<MomentParticipant> getParticipants() {
            return (List<MomentParticipant>) get("participants");
        }

        public Moment setParticipants(List<MomentParticipant> arg) {
            optimisticData.put(getKey("participants"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "live": return false;

                case "startTime": return false;

                case "endTime": return false;

                case "priority": return true;

                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "id": return false;

                case "space": return true;

                case "mentioned": return true;

                case "messages": return true;

                case "updatedBy": return true;

                case "keyMessage": return true;

                case "summaryPhrases": return false;

                case "participants": return true;

                default: return false;
            }
        }
    }

    public interface MomentCollectionQueryDefinition {
        void define(MomentCollectionQuery _queryBuilder);
    }

    public static class MomentCollectionQuery extends Query<MomentCollectionQuery> {
        MomentCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MomentCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MomentCollectionQuery items(MomentQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new MomentQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MomentCollection extends AbstractResponse<MomentCollection> {
        public MomentCollection() {
        }

        public MomentCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Moment> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Moment optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Moment(jsonAsObject(element1, key));
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

        public String getGraphQlTypeName() {
            return "MomentCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MomentCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<Moment> getItems() {
            return (List<Moment>) get("items");
        }

        public MomentCollection setItems(List<Moment> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface MomentParticipantQueryDefinition {
        void define(MomentParticipantQuery _queryBuilder);
    }

    /**
    * A single participant object
    */
    public static class MomentParticipantQuery extends Query<MomentParticipantQuery> {
        MomentParticipantQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MomentParticipantQuery messageCount() {
            startField("messageCount");

            return this;
        }

        public MomentParticipantQuery user(PersonQueryDefinition queryDef) {
            startField("user");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MomentParticipantQuery viaAppUsers(AppUserQueryDefinition queryDef) {
            startField("viaAppUsers");

            _queryBuilder.append('{');
            queryDef.define(new AppUserQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single participant object
    */
    public static class MomentParticipant extends AbstractResponse<MomentParticipant> {
        public MomentParticipant() {
        }

        public MomentParticipant(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "messageCount": {
                        Integer optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsInteger(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "user": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "viaAppUsers": {
                        List<AppUser> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<AppUser> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                AppUser optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = new AppUser(jsonAsObject(element1, key));
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

        public String getGraphQlTypeName() {
            return "MomentParticipant";
        }

        public Integer getMessageCount() {
            return (Integer) get("messageCount");
        }

        public MomentParticipant setMessageCount(Integer arg) {
            optimisticData.put(getKey("messageCount"), arg);
            return this;
        }

        public Person getUser() {
            return (Person) get("user");
        }

        public MomentParticipant setUser(Person arg) {
            optimisticData.put(getKey("user"), arg);
            return this;
        }

        public List<AppUser> getViaAppUsers() {
            return (List<AppUser>) get("viaAppUsers");
        }

        public MomentParticipant setViaAppUsers(List<AppUser> arg) {
            optimisticData.put(getKey("viaAppUsers"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "messageCount": return false;

                case "user": return true;

                case "viaAppUsers": return true;

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

        /**
        * Create a Space
        */
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

        /**
        * Update a teams settings.
        */
        public MutationRootQuery updateTeam(TeamMutationInput input, TeamMutationQueryDefinition queryDef) {
            startField("updateTeam");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new TeamMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Update a Space
        */
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

        public MutationRootQuery createMessage(CreateMessageInput input, MessageMutationQueryDefinition queryDef) {
            startField("createMessage");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Remove existing members from a space
        */
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

        /**
        * [Beta] Create a targeted message
        */
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

        /**
        * Delete a Space
        */
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

        /**
        * Add new members to a space
        */
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

                    case "updateTeam": {
                        TeamMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TeamMutation(jsonAsObject(field.getValue(), key));
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

                    case "createMessage": {
                        MessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageMutation(jsonAsObject(field.getValue(), key));
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

                    case "addMessageFocus": {
                        MessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageMutation(jsonAsObject(field.getValue(), key));
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

                    case "deleteSpace": {
                        DeleteMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new DeleteMutation(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "MutationRoot";
        }

        /**
        * Create a Space
        */

        public SpaceMutation getCreateSpace() {
            return (SpaceMutation) get("createSpace");
        }

        public MutationRoot setCreateSpace(SpaceMutation arg) {
            optimisticData.put(getKey("createSpace"), arg);
            return this;
        }

        /**
        * Update a teams settings.
        */

        public TeamMutation getUpdateTeam() {
            return (TeamMutation) get("updateTeam");
        }

        public MutationRoot setUpdateTeam(TeamMutation arg) {
            optimisticData.put(getKey("updateTeam"), arg);
            return this;
        }

        /**
        * Update a Space
        */

        public SpaceMutation getUpdateSpace() {
            return (SpaceMutation) get("updateSpace");
        }

        public MutationRoot setUpdateSpace(SpaceMutation arg) {
            optimisticData.put(getKey("updateSpace"), arg);
            return this;
        }

        public MessageMutation getCreateMessage() {
            return (MessageMutation) get("createMessage");
        }

        public MutationRoot setCreateMessage(MessageMutation arg) {
            optimisticData.put(getKey("createMessage"), arg);
            return this;
        }

        /**
        * Remove existing members from a space
        */

        public SpaceMutation getRemoveSpaceMembers() {
            return (SpaceMutation) get("removeSpaceMembers");
        }

        public MutationRoot setRemoveSpaceMembers(SpaceMutation arg) {
            optimisticData.put(getKey("removeSpaceMembers"), arg);
            return this;
        }

        public MessageMutation getAddMessageFocus() {
            return (MessageMutation) get("addMessageFocus");
        }

        public MutationRoot setAddMessageFocus(MessageMutation arg) {
            optimisticData.put(getKey("addMessageFocus"), arg);
            return this;
        }

        /**
        * [Beta] Create a targeted message
        */

        public TargetedMessageMutation getCreateTargetedMessage() {
            return (TargetedMessageMutation) get("createTargetedMessage");
        }

        public MutationRoot setCreateTargetedMessage(TargetedMessageMutation arg) {
            optimisticData.put(getKey("createTargetedMessage"), arg);
            return this;
        }

        /**
        * Delete a Space
        */

        public DeleteMutation getDeleteSpace() {
            return (DeleteMutation) get("deleteSpace");
        }

        public MutationRoot setDeleteSpace(DeleteMutation arg) {
            optimisticData.put(getKey("deleteSpace"), arg);
            return this;
        }

        /**
        * Add new members to a space
        */

        public SpaceMutation getAddSpaceMembers() {
            return (SpaceMutation) get("addSpaceMembers");
        }

        public MutationRoot setAddSpaceMembers(SpaceMutation arg) {
            optimisticData.put(getKey("addSpaceMembers"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "createSpace": return true;

                case "updateTeam": return true;

                case "updateSpace": return true;

                case "createMessage": return true;

                case "removeSpaceMembers": return true;

                case "addMessageFocus": return true;

                case "createTargetedMessage": return true;

                case "deleteSpace": return true;

                case "addSpaceMembers": return true;

                default: return false;
            }
        }
    }

    public interface NodeQueryDefinition {
        void define(NodeQuery _queryBuilder);
    }

    /**
    * A single node object
    */
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

        public NodeQuery onMoment(MomentQueryDefinition queryDef) {
            startInlineFragment("Moment");
            queryDef.define(new MomentQuery(_queryBuilder));
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

    public interface Node extends com.shopify.graphql.support.Node {
        String getGraphQlTypeName();

        ID getId();
    }

    /**
    * A single node object
    */
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

        public static Node create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "Conversation": {
                    return new Conversation(fields);
                }

                case "Message": {
                    return new Message(fields);
                }

                case "Moment": {
                    return new Moment(fields);
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
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

        public String getGraphQlTypeName() {
            return "PageInfo";
        }

        public String getStartCursor() {
            return (String) get("startCursor");
        }

        public PageInfo setStartCursor(String arg) {
            optimisticData.put(getKey("startCursor"), arg);
            return this;
        }

        public String getEndCursor() {
            return (String) get("endCursor");
        }

        public PageInfo setEndCursor(String arg) {
            optimisticData.put(getKey("endCursor"), arg);
            return this;
        }

        public Boolean getHasPreviousPage() {
            return (Boolean) get("hasPreviousPage");
        }

        public PageInfo setHasPreviousPage(Boolean arg) {
            optimisticData.put(getKey("hasPreviousPage"), arg);
            return this;
        }

        public Boolean getHasNextPage() {
            return (Boolean) get("hasNextPage");
        }

        public PageInfo setHasNextPage(Boolean arg) {
            optimisticData.put(getKey("hasNextPage"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    /**
    * A single person object
    */
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

        /**
        * Unique internal record identifier assigned when a user first obtains an IBMid (registered users
        * only).
        */
        public PersonQuery ibmUniqueId() {
            startField("ibmUniqueID");

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

        /**
        * Person that created the entity
        */
        public PersonQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public PersonQuery presence() {
            startField("presence");

            return this;
        }

        /**
        * Person that updated the entity last
        */
        public PersonQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Retrieve a list of teams the calling user and the person have in common.
        */
        public PersonQuery teams(TeamCollectionQueryDefinition queryDef) {
            startField("teams");

            _queryBuilder.append('{');
            queryDef.define(new TeamCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Indicates whether or not a user is active in Watson Work.
        */
        public PersonQuery accountStatus(PersonAccountStatusQueryDefinition queryDef) {
            startField("accountStatus");

            _queryBuilder.append('{');
            queryDef.define(new PersonAccountStatusQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single person object
    */
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

                    case "ibmUniqueID": {
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

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

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "teams": {
                        TeamCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TeamCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "accountStatus": {
                        PersonAccountStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonAccountStatus(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "Person";
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public Person setDisplayName(String arg) {
            optimisticData.put(getKey("displayName"), arg);
            return this;
        }

        public String getExtId() {
            return (String) get("extId");
        }

        public Person setExtId(String arg) {
            optimisticData.put(getKey("extId"), arg);
            return this;
        }

        public String getEmail() {
            return (String) get("email");
        }

        public Person setEmail(String arg) {
            optimisticData.put(getKey("email"), arg);
            return this;
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public Person setPhotoUrl(String arg) {
            optimisticData.put(getKey("photoUrl"), arg);
            return this;
        }

        public String getCustomerId() {
            return (String) get("customerId");
        }

        public Person setCustomerId(String arg) {
            optimisticData.put(getKey("customerId"), arg);
            return this;
        }

        public String getType() {
            return (String) get("type");
        }

        public Person setType(String arg) {
            optimisticData.put(getKey("type"), arg);
            return this;
        }

        public String getDirectMessageSpaceId() {
            return (String) get("directMessageSpaceId");
        }

        public Person setDirectMessageSpaceId(String arg) {
            optimisticData.put(getKey("directMessageSpaceId"), arg);
            return this;
        }

        /**
        * Unique internal record identifier assigned when a user first obtains an IBMid (registered users
        * only).
        */

        public String getIbmUniqueId() {
            return (String) get("ibmUniqueID");
        }

        public Person setIbmUniqueId(String arg) {
            optimisticData.put(getKey("ibmUniqueID"), arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Person setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Person setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Person setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        public PresenceStatus getPresence() {
            return (PresenceStatus) get("presence");
        }

        public Person setPresence(PresenceStatus arg) {
            optimisticData.put(getKey("presence"), arg);
            return this;
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Person setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        /**
        * Retrieve a list of teams the calling user and the person have in common.
        */

        public TeamCollection getTeams() {
            return (TeamCollection) get("teams");
        }

        public Person setTeams(TeamCollection arg) {
            optimisticData.put(getKey("teams"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        /**
        * Indicates whether or not a user is active in Watson Work.
        */

        public PersonAccountStatus getAccountStatus() {
            return (PersonAccountStatus) get("accountStatus");
        }

        public Person setAccountStatus(PersonAccountStatus arg) {
            optimisticData.put(getKey("accountStatus"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "displayName": return false;

                case "extId": return false;

                case "email": return false;

                case "photoUrl": return false;

                case "customerId": return false;

                case "type": return false;

                case "directMessageSpaceId": return false;

                case "ibmUniqueID": return false;

                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "presence": return false;

                case "updatedBy": return true;

                case "teams": return true;

                case "id": return false;

                case "accountStatus": return true;

                default: return false;
            }
        }
    }

    public interface PersonAccountStatusQueryDefinition {
        void define(PersonAccountStatusQuery _queryBuilder);
    }

    public static class PersonAccountStatusQuery extends Query<PersonAccountStatusQuery> {
        PersonAccountStatusQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public PersonAccountStatusQuery active() {
            startField("active");

            return this;
        }
    }

    public static class PersonAccountStatus extends AbstractResponse<PersonAccountStatus> {
        public PersonAccountStatus() {
        }

        public PersonAccountStatus(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "active": {
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

        public String getGraphQlTypeName() {
            return "PersonAccountStatus";
        }

        public Boolean getActive() {
            return (Boolean) get("active");
        }

        public PersonAccountStatus setActive(Boolean arg) {
            optimisticData.put(getKey("active"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "active": return false;

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

        public String getGraphQlTypeName() {
            return "PersonCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public PersonCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<Person> getItems() {
            return (List<Person>) get("items");
        }

        public PersonCollection setItems(List<Person> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    public enum PriorityFeatureType {
        PARTICIPANT,

        PHRASE,

        USER_MARK,

        UNKNOWN_VALUE;

        public static PriorityFeatureType fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "PARTICIPANT": {
                    return PARTICIPANT;
                }

                case "PHRASE": {
                    return PHRASE;
                }

                case "USER_MARK": {
                    return USER_MARK;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case PARTICIPANT: {
                    return "PARTICIPANT";
                }

                case PHRASE: {
                    return "PHRASE";
                }

                case USER_MARK: {
                    return "USER_MARK";
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

    /**
    * Master QueryRoot
    */
    public static class QueryRootQuery extends Query<QueryRootQuery> {
        QueryRootQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        /**
        * Retrieve a space by its id
        */
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

        /**
        * Retrieve information about the calling user
        */
        public QueryRootQuery me(PersonQueryDefinition queryDef) {
            startField("me");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Retrieve a conversation by its id
        */
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

        /**
        * Search for spaces by title (optionally sort by "activity")
        */
        public QueryRootQuery searchSpaces(String title, SpaceCollectionQueryDefinition queryDef) {
            return searchSpaces(title, args -> {}, queryDef);
        }

        /**
        * Search for spaces by title (optionally sort by "activity")
        */
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

        /**
        * Retrieve a list of the calling user's teams
        */
        public QueryRootQuery teams(TeamCollectionQueryDefinition queryDef) {
            startField("teams");

            _queryBuilder.append('{');
            queryDef.define(new TeamCollectionQuery(_queryBuilder));
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

        /**
        * Retrieve a person by their ID or email address (ID takes precedence).
        */
        public QueryRootQuery person(PersonQueryDefinition queryDef) {
            return person(args -> {}, queryDef);
        }

        /**
        * Retrieve a person by their ID or email address (ID takes precedence).
        */
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

        /**
        * Retrieve a list of the calling user's spaces sorted by updated time
        */
        public QueryRootQuery spaces(SpaceCollectionQueryDefinition queryDef) {
            return spaces(args -> {}, queryDef);
        }

        /**
        * Retrieve a list of the calling user's spaces sorted by updated time
        */
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

        /**
        * Retrieve a list of teams the calling user is an admin of.
        */
        public QueryRootQuery adminTeams(TeamCollectionQueryDefinition queryDef) {
            startField("adminTeams");

            _queryBuilder.append('{');
            queryDef.define(new TeamCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class MomentArguments extends Arguments {
            MomentArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MomentArguments id(ID value) {
                if (value != null) {
                    startArgument("id");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }
        }

        public interface MomentArgumentsDefinition {
            void define(MomentArguments args);
        }

        /**
        * Retrieve a moment by its id
        */
        public QueryRootQuery moment(MomentQueryDefinition queryDef) {
            return moment(args -> {}, queryDef);
        }

        /**
        * Retrieve a moment by its id
        */
        public QueryRootQuery moment(MomentArgumentsDefinition argsDef, MomentQueryDefinition queryDef) {
            startField("moment");

            MomentArguments args = new MomentArguments(_queryBuilder);
            argsDef.define(args);
            MomentArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MomentQuery(_queryBuilder));
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

        /**
        * Retrieve a list of the calling user's mentions
        */
        public QueryRootQuery mentioned(MentionCollectionQueryDefinition queryDef) {
            return mentioned(args -> {}, queryDef);
        }

        /**
        * Retrieve a list of the calling user's mentions
        */
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

        /**
        * Search for messages (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */
        public QueryRootQuery searchMessages(String query, MessageSearchCollectionQueryDefinition queryDef) {
            return searchMessages(query, args -> {}, queryDef);
        }

        /**
        * Search for messages (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */
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

        /**
        * Retrieve a message by its id
        */
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

        /**
        * Retrieve a list of Toscana users from the calling user's organisation
        */
        public QueryRootQuery people(PersonCollectionQueryDefinition queryDef) {
            return people(args -> {}, queryDef);
        }

        /**
        * Retrieve a list of Toscana users from the calling user's organisation
        */
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

        /**
        * Retrieve all members of a space or search for them by name (see
        * https://developer.watsonwork.ibm.com/docs)
        */
        public QueryRootQuery searchSpaceMembers(SpaceMembersSearchCollectionQueryDefinition queryDef) {
            return searchSpaceMembers(args -> {}, queryDef);
        }

        /**
        * Retrieve all members of a space or search for them by name (see
        * https://developer.watsonwork.ibm.com/docs)
        */
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

        public class MomentsArguments extends Arguments {
            MomentsArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, false);
            }

            public MomentsArguments predicted(Boolean value) {
                if (value != null) {
                    startArgument("predicted");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MomentsArguments oldestTimestamp(String value) {
                if (value != null) {
                    startArgument("oldestTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments mostRecentTimestamp(String value) {
                if (value != null) {
                    startArgument("mostRecentTimestamp");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public MomentsArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MomentsArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MomentsArgumentsDefinition {
            void define(MomentsArguments args);
        }

        /**
        * Retrieve a list of the conversation's moments sorted by startTime
        */
        public QueryRootQuery moments(String spaceId, MomentCollectionQueryDefinition queryDef) {
            return moments(spaceId, args -> {}, queryDef);
        }

        /**
        * Retrieve a list of the conversation's moments sorted by startTime
        */
        public QueryRootQuery moments(String spaceId, MomentsArgumentsDefinition argsDef, MomentCollectionQueryDefinition queryDef) {
            startField("moments");

            _queryBuilder.append("(spaceId:");
            Query.appendQuotedString(_queryBuilder, spaceId.toString());

            argsDef.define(new MomentsArguments(_queryBuilder));

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MomentCollectionQuery(_queryBuilder));
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

        /**
        * Search for people by name (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */
        public QueryRootQuery searchPeople(String name, PersonCollectionQueryDefinition queryDef) {
            return searchPeople(name, args -> {}, queryDef);
        }

        /**
        * Search for people by name (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */
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

        public String toString() {
            return _queryBuilder.toString();
        }
    }

    /**
    * Master QueryRoot
    */
    public static class QueryRoot extends AbstractResponse<QueryRoot> {
        public QueryRoot() {
        }

        public QueryRoot(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "me": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
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

                    case "searchSpaces": {
                        SpaceCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "teams": {
                        TeamCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TeamCollection(jsonAsObject(field.getValue(), key));
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

                    case "spaces": {
                        SpaceCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "adminTeams": {
                        TeamCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TeamCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "moment": {
                        Moment optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Moment(jsonAsObject(field.getValue(), key));
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

                    case "searchMessages": {
                        MessageSearchCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageSearchCollection(jsonAsObject(field.getValue(), key));
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

                    case "people": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
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

                    case "moments": {
                        MomentCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MomentCollection(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "QueryRoot";
        }

        /**
        * Retrieve a space by its id
        */

        public Space getSpace() {
            return (Space) get("space");
        }

        public QueryRoot setSpace(Space arg) {
            optimisticData.put(getKey("space"), arg);
            return this;
        }

        /**
        * Retrieve information about the calling user
        */

        public Person getMe() {
            return (Person) get("me");
        }

        public QueryRoot setMe(Person arg) {
            optimisticData.put(getKey("me"), arg);
            return this;
        }

        /**
        * Retrieve a conversation by its id
        */

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public QueryRoot setConversation(Conversation arg) {
            optimisticData.put(getKey("conversation"), arg);
            return this;
        }

        /**
        * Search for spaces by title (optionally sort by "activity")
        */

        public SpaceCollection getSearchSpaces() {
            return (SpaceCollection) get("searchSpaces");
        }

        public QueryRoot setSearchSpaces(SpaceCollection arg) {
            optimisticData.put(getKey("searchSpaces"), arg);
            return this;
        }

        /**
        * Retrieve a list of the calling user's teams
        */

        public TeamCollection getTeams() {
            return (TeamCollection) get("teams");
        }

        public QueryRoot setTeams(TeamCollection arg) {
            optimisticData.put(getKey("teams"), arg);
            return this;
        }

        /**
        * Retrieve a person by their ID or email address (ID takes precedence).
        */

        public Person getPerson() {
            return (Person) get("person");
        }

        public QueryRoot setPerson(Person arg) {
            optimisticData.put(getKey("person"), arg);
            return this;
        }

        /**
        * Retrieve a list of the calling user's spaces sorted by updated time
        */

        public SpaceCollection getSpaces() {
            return (SpaceCollection) get("spaces");
        }

        public QueryRoot setSpaces(SpaceCollection arg) {
            optimisticData.put(getKey("spaces"), arg);
            return this;
        }

        /**
        * Retrieve a list of teams the calling user is an admin of.
        */

        public TeamCollection getAdminTeams() {
            return (TeamCollection) get("adminTeams");
        }

        public QueryRoot setAdminTeams(TeamCollection arg) {
            optimisticData.put(getKey("adminTeams"), arg);
            return this;
        }

        /**
        * Retrieve a moment by its id
        */

        public Moment getMoment() {
            return (Moment) get("moment");
        }

        public QueryRoot setMoment(Moment arg) {
            optimisticData.put(getKey("moment"), arg);
            return this;
        }

        /**
        * Retrieve a list of the calling user's mentions
        */

        public MentionCollection getMentioned() {
            return (MentionCollection) get("mentioned");
        }

        public QueryRoot setMentioned(MentionCollection arg) {
            optimisticData.put(getKey("mentioned"), arg);
            return this;
        }

        /**
        * Search for messages (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */

        public MessageSearchCollection getSearchMessages() {
            return (MessageSearchCollection) get("searchMessages");
        }

        public QueryRoot setSearchMessages(MessageSearchCollection arg) {
            optimisticData.put(getKey("searchMessages"), arg);
            return this;
        }

        /**
        * Retrieve a message by its id
        */

        public Message getMessage() {
            return (Message) get("message");
        }

        public QueryRoot setMessage(Message arg) {
            optimisticData.put(getKey("message"), arg);
            return this;
        }

        /**
        * Retrieve a list of Toscana users from the calling user's organisation
        */

        public PersonCollection getPeople() {
            return (PersonCollection) get("people");
        }

        public QueryRoot setPeople(PersonCollection arg) {
            optimisticData.put(getKey("people"), arg);
            return this;
        }

        /**
        * Retrieve all members of a space or search for them by name (see
        * https://developer.watsonwork.ibm.com/docs)
        */

        public SpaceMembersSearchCollection getSearchSpaceMembers() {
            return (SpaceMembersSearchCollection) get("searchSpaceMembers");
        }

        public QueryRoot setSearchSpaceMembers(SpaceMembersSearchCollection arg) {
            optimisticData.put(getKey("searchSpaceMembers"), arg);
            return this;
        }

        /**
        * Retrieve a list of the conversation's moments sorted by startTime
        */

        public MomentCollection getMoments() {
            return (MomentCollection) get("moments");
        }

        public QueryRoot setMoments(MomentCollection arg) {
            optimisticData.put(getKey("moments"), arg);
            return this;
        }

        /**
        * Search for people by name (see https://developer.watsonwork.ibm.com/docs for argument descriptions)
        */

        public PersonCollection getSearchPeople() {
            return (PersonCollection) get("searchPeople");
        }

        public QueryRoot setSearchPeople(PersonCollection arg) {
            optimisticData.put(getKey("searchPeople"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "space": return true;

                case "me": return true;

                case "conversation": return true;

                case "searchSpaces": return true;

                case "teams": return true;

                case "person": return true;

                case "spaces": return true;

                case "adminTeams": return true;

                case "moment": return true;

                case "mentioned": return true;

                case "searchMessages": return true;

                case "message": return true;

                case "people": return true;

                case "searchSpaceMembers": return true;

                case "moments": return true;

                case "searchPeople": return true;

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

    public interface RoleQueryDefinition {
        void define(RoleQuery _queryBuilder);
    }

    public static class RoleQuery extends Query<RoleQuery> {
        RoleQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public RoleQuery id() {
            startField("id");

            return this;
        }

        public RoleQuery role() {
            startField("role");

            return this;
        }
    }

    public static class Role extends AbstractResponse<Role> {
        public Role() {
        }

        public Role(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "role": {
                        responseData.put(key, RoleEnum.fromGraphQl(jsonAsString(field.getValue(), key)));

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

        public String getGraphQlTypeName() {
            return "Role";
        }

        public ID getId() {
            return (ID) get("id");
        }

        public Role setId(ID arg) {
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        public RoleEnum getRole() {
            return (RoleEnum) get("role");
        }

        public Role setRole(RoleEnum arg) {
            optimisticData.put(getKey("role"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "id": return false;

                case "role": return false;

                default: return false;
            }
        }
    }

    public enum RoleEnum {
        ADMIN,

        MEMBER,

        UNKNOWN_VALUE;

        public static RoleEnum fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "ADMIN": {
                    return ADMIN;
                }

                case "MEMBER": {
                    return MEMBER;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case ADMIN: {
                    return "ADMIN";
                }

                case MEMBER: {
                    return "MEMBER";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public static class RoleInput implements Serializable {
        private ID id;

        private RoleEnum role;

        public RoleInput(ID id, RoleEnum role) {
            this.id = id;

            this.role = role;
        }

        public ID getId() {
            return id;
        }

        public RoleInput setId(ID id) {
            this.id = id;
            return this;
        }

        public RoleEnum getRole() {
            return role;
        }

        public RoleInput setRole(RoleEnum role) {
            this.role = role;
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
            _queryBuilder.append("role:");
            _queryBuilder.append(role.toString());

            _queryBuilder.append('}');
        }
    }

    public interface SpaceQueryDefinition {
        void define(SpaceQuery _queryBuilder);
    }

    /**
    * A single space object
    */
    public static class SpaceQuery extends Query<SpaceQuery> {
        SpaceQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public SpaceQuery title() {
            startField("title");

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

        /**
        * Person that created the entity
        */
        public SpaceQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
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

        /**
        * A space's members
        */
        public SpaceQuery members(PersonCollectionQueryDefinition queryDef) {
            return members(args -> {}, queryDef);
        }

        /**
        * A space's members
        */
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

        /**
        * A space's conversation
        */
        public SpaceQuery conversation(ConversationQueryDefinition queryDef) {
            startField("conversation");

            _queryBuilder.append('{');
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Person that updated the entity last
        */
        public SpaceQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery description() {
            startField("description");

            return this;
        }

        /**
        * Last updated time for the space's members
        */
        public SpaceQuery membersUpdated() {
            startField("membersUpdated");

            return this;
        }

        public SpaceQuery team(TeamQueryDefinition queryDef) {
            startField("team");

            _queryBuilder.append('{');
            queryDef.define(new TeamQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single space object
    */
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
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

                    case "conversation": {
                        Conversation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Conversation(jsonAsObject(field.getValue(), key));
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

                    case "description": {
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

                    case "team": {
                        Team optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Team(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "Space";
        }

        public String getTitle() {
            return (String) get("title");
        }

        public Space setTitle(String arg) {
            optimisticData.put(getKey("title"), arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Space setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Space setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Space setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        /**
        * A space's members
        */

        public PersonCollection getMembers() {
            return (PersonCollection) get("members");
        }

        public Space setMembers(PersonCollection arg) {
            optimisticData.put(getKey("members"), arg);
            return this;
        }

        /**
        * A space's conversation
        */

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public Space setConversation(Conversation arg) {
            optimisticData.put(getKey("conversation"), arg);
            return this;
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Space setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        public String getDescription() {
            return (String) get("description");
        }

        public Space setDescription(String arg) {
            optimisticData.put(getKey("description"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        /**
        * Last updated time for the space's members
        */

        public String getMembersUpdated() {
            return (String) get("membersUpdated");
        }

        public Space setMembersUpdated(String arg) {
            optimisticData.put(getKey("membersUpdated"), arg);
            return this;
        }

        public Team getTeam() {
            return (Team) get("team");
        }

        public Space setTeam(Team arg) {
            optimisticData.put(getKey("team"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "title": return false;

                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "members": return true;

                case "conversation": return true;

                case "updatedBy": return true;

                case "description": return false;

                case "id": return false;

                case "membersUpdated": return false;

                case "team": return true;

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

        public String getGraphQlTypeName() {
            return "SpaceCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public SpaceCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<Space> getItems() {
            return (List<Space>) get("items");
        }

        public SpaceCollection setItems(List<Space> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

        /**
        * Unique internal record identifier assigned when a user first obtains an IBMid (registered users
        * only).
        */
        public SpaceMemberQuery ibmUniqueId() {
            startField("ibmUniqueID");

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

        /**
        * Person that created the entity
        */
        public SpaceMemberQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceMemberQuery presence() {
            startField("presence");

            return this;
        }

        /**
        * Person that updated the entity last
        */
        public SpaceMemberQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        /**
        * Retrieve a list of teams the calling user and the person have in common.
        */
        public SpaceMemberQuery teams(TeamCollectionQueryDefinition queryDef) {
            startField("teams");

            _queryBuilder.append('{');
            queryDef.define(new TeamCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceMemberQuery id() {
            startField("id");

            return this;
        }

        /**
        * Indicates whether or not a user is active in Watson Work.
        */
        public SpaceMemberQuery accountStatus(PersonAccountStatusQueryDefinition queryDef) {
            startField("accountStatus");

            _queryBuilder.append('{');
            queryDef.define(new PersonAccountStatusQuery(_queryBuilder));
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

                    case "ibmUniqueID": {
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

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

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

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "teams": {
                        TeamCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TeamCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "accountStatus": {
                        PersonAccountStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonAccountStatus(jsonAsObject(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "SpaceMember";
        }

        public List<String> getPermissions() {
            return (List<String>) get("permissions");
        }

        public SpaceMember setPermissions(List<String> arg) {
            optimisticData.put(getKey("permissions"), arg);
            return this;
        }

        public List<SpaceRole> getRoles() {
            return (List<SpaceRole>) get("roles");
        }

        public SpaceMember setRoles(List<SpaceRole> arg) {
            optimisticData.put(getKey("roles"), arg);
            return this;
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public SpaceMember setDisplayName(String arg) {
            optimisticData.put(getKey("displayName"), arg);
            return this;
        }

        public String getExtId() {
            return (String) get("extId");
        }

        public SpaceMember setExtId(String arg) {
            optimisticData.put(getKey("extId"), arg);
            return this;
        }

        public String getEmail() {
            return (String) get("email");
        }

        public SpaceMember setEmail(String arg) {
            optimisticData.put(getKey("email"), arg);
            return this;
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public SpaceMember setPhotoUrl(String arg) {
            optimisticData.put(getKey("photoUrl"), arg);
            return this;
        }

        public String getCustomerId() {
            return (String) get("customerId");
        }

        public SpaceMember setCustomerId(String arg) {
            optimisticData.put(getKey("customerId"), arg);
            return this;
        }

        public String getType() {
            return (String) get("type");
        }

        public SpaceMember setType(String arg) {
            optimisticData.put(getKey("type"), arg);
            return this;
        }

        public String getDirectMessageSpaceId() {
            return (String) get("directMessageSpaceId");
        }

        public SpaceMember setDirectMessageSpaceId(String arg) {
            optimisticData.put(getKey("directMessageSpaceId"), arg);
            return this;
        }

        /**
        * Unique internal record identifier assigned when a user first obtains an IBMid (registered users
        * only).
        */

        public String getIbmUniqueId() {
            return (String) get("ibmUniqueID");
        }

        public SpaceMember setIbmUniqueId(String arg) {
            optimisticData.put(getKey("ibmUniqueID"), arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public SpaceMember setCreated(String arg) {
            optimisticData.put(getKey("created"), arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public SpaceMember setUpdated(String arg) {
            optimisticData.put(getKey("updated"), arg);
            return this;
        }

        /**
        * Person that created the entity
        */

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public SpaceMember setCreatedBy(Person arg) {
            optimisticData.put(getKey("createdBy"), arg);
            return this;
        }

        public PresenceStatus getPresence() {
            return (PresenceStatus) get("presence");
        }

        public SpaceMember setPresence(PresenceStatus arg) {
            optimisticData.put(getKey("presence"), arg);
            return this;
        }

        /**
        * Person that updated the entity last
        */

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public SpaceMember setUpdatedBy(Person arg) {
            optimisticData.put(getKey("updatedBy"), arg);
            return this;
        }

        /**
        * Retrieve a list of teams the calling user and the person have in common.
        */

        public TeamCollection getTeams() {
            return (TeamCollection) get("teams");
        }

        public SpaceMember setTeams(TeamCollection arg) {
            optimisticData.put(getKey("teams"), arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public SpaceMember setId(ID arg) {
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        /**
        * Indicates whether or not a user is active in Watson Work.
        */

        public PersonAccountStatus getAccountStatus() {
            return (PersonAccountStatus) get("accountStatus");
        }

        public SpaceMember setAccountStatus(PersonAccountStatus arg) {
            optimisticData.put(getKey("accountStatus"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "permissions": return false;

                case "roles": return false;

                case "displayName": return false;

                case "extId": return false;

                case "email": return false;

                case "photoUrl": return false;

                case "customerId": return false;

                case "type": return false;

                case "directMessageSpaceId": return false;

                case "ibmUniqueID": return false;

                case "created": return false;

                case "updated": return false;

                case "createdBy": return true;

                case "presence": return false;

                case "updatedBy": return true;

                case "teams": return true;

                case "id": return false;

                case "accountStatus": return true;

                default: return false;
            }
        }
    }

    public static class SpaceMemberInput implements Serializable {
        private String id;

        private Input<List<String>> permissions = Input.undefined();

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
            return permissions.getValue();
        }

        public Input<List<String>> getPermissionsInput() {
            return permissions;
        }

        public SpaceMemberInput setPermissions(List<String> permissions) {
            this.permissions = Input.optional(permissions);
            return this;
        }

        public SpaceMemberInput setPermissionsInput(Input<List<String>> permissions) {
            if (permissions == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.permissions.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("permissions:");
                if (permissions.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : permissions.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
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

        public SpaceMembersSearchCollectionQuery total() {
            startField("total");

            return this;
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

        public String getGraphQlTypeName() {
            return "SpaceMembersSearchCollection";
        }

        public Integer getTotal() {
            return (Integer) get("total");
        }

        public SpaceMembersSearchCollection setTotal(Integer arg) {
            optimisticData.put(getKey("total"), arg);
            return this;
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public SpaceMembersSearchCollection setPageInfo(PageInfo arg) {
            optimisticData.put(getKey("pageInfo"), arg);
            return this;
        }

        public List<SpaceMember> getItems() {
            return (List<SpaceMember>) get("items");
        }

        public SpaceMembersSearchCollection setItems(List<SpaceMember> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "total": return false;

                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface SpaceMutationQueryDefinition {
        void define(SpaceMutationQuery _queryBuilder);
    }

    /**
    * A mutation object from a space
    */
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

    /**
    * A mutation object from a space
    */
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

        public String getGraphQlTypeName() {
            return "SpaceMutation";
        }

        public List<String> getMemberIdsChanged() {
            return (List<String>) get("memberIdsChanged");
        }

        public SpaceMutation setMemberIdsChanged(List<String> arg) {
            optimisticData.put(getKey("memberIdsChanged"), arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public SpaceMutation setSpace(Space arg) {
            optimisticData.put(getKey("space"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
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

    public interface SubscriptionQueryDefinition {
        void define(SubscriptionQuery _queryBuilder);
    }

    /**
    * SSM concept defining a set of capabilities available to the user having a seat in this subscription
    */
    public static class SubscriptionQuery extends Query<SubscriptionQuery> {
        SubscriptionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SubscriptionQuery id() {
            startField("id");

            return this;
        }
    }

    /**
    * SSM concept defining a set of capabilities available to the user having a seat in this subscription
    */
    public static class Subscription extends AbstractResponse<Subscription> {
        public Subscription() {
        }

        public Subscription(JsonObject fields) throws SchemaViolationError {
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

        public String getGraphQlTypeName() {
            return "Subscription";
        }

        public ID getId() {
            return (ID) get("id");
        }

        public Subscription setId(ID arg) {
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "id": return false;

                default: return false;
            }
        }
    }

    public interface SummaryPhraseQueryDefinition {
        void define(SummaryPhraseQuery _queryBuilder);
    }

    /**
    * A single summary phrase object
    */
    public static class SummaryPhraseQuery extends Query<SummaryPhraseQuery> {
        SummaryPhraseQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("__typename");
        }

        public SummaryPhraseQuery label() {
            startField("label");

            return this;
        }

        public SummaryPhraseQuery score() {
            startField("score");

            return this;
        }

        public SummaryPhraseQuery onEntity(EntityQueryDefinition queryDef) {
            startInlineFragment("Entity");
            queryDef.define(new EntityQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SummaryPhraseQuery onKeyword(KeywordQueryDefinition queryDef) {
            startInlineFragment("Keyword");
            queryDef.define(new KeywordQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SummaryPhraseQuery onTaxonomy(TaxonomyQueryDefinition queryDef) {
            startInlineFragment("Taxonomy");
            queryDef.define(new TaxonomyQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }
    }

    public interface SummaryPhrase {
        String getGraphQlTypeName();

        String getLabel();

        Double getScore();
    }

    /**
    * A single summary phrase object
    */
    public static class UnknownSummaryPhrase extends AbstractResponse<UnknownSummaryPhrase> implements SummaryPhrase {
        public UnknownSummaryPhrase() {
        }

        public UnknownSummaryPhrase(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
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

        public static SummaryPhrase create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "Entity": {
                    return new Entity(fields);
                }

                case "Keyword": {
                    return new Keyword(fields);
                }

                case "Taxonomy": {
                    return new Taxonomy(fields);
                }

                default: {
                    return new UnknownSummaryPhrase(fields);
                }
            }
        }

        public String getGraphQlTypeName() {
            return (String) get("__typename");
        }

        public String getLabel() {
            return (String) get("label");
        }

        public UnknownSummaryPhrase setLabel(String arg) {
            optimisticData.put(getKey("label"), arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public UnknownSummaryPhrase setScore(Double arg) {
            optimisticData.put(getKey("score"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public interface SupportingFeatureQueryDefinition {
        void define(SupportingFeatureQuery _queryBuilder);
    }

    /**
    * An abstract representation of something which supports a priority prediction. Implementing classes
    * represent specific types of features.
    */
    public static class SupportingFeatureQuery extends Query<SupportingFeatureQuery> {
        SupportingFeatureQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("__typename");
        }

        public SupportingFeatureQuery category() {
            startField("category");

            return this;
        }

        public SupportingFeatureQuery onSupportingParticipant(SupportingParticipantQueryDefinition queryDef) {
            startInlineFragment("SupportingParticipant");
            queryDef.define(new SupportingParticipantQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SupportingFeatureQuery onSupportingPhrase(SupportingPhraseQueryDefinition queryDef) {
            startInlineFragment("SupportingPhrase");
            queryDef.define(new SupportingPhraseQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SupportingFeatureQuery onSupportingUserMark(SupportingUserMarkQueryDefinition queryDef) {
            startInlineFragment("SupportingUserMark");
            queryDef.define(new SupportingUserMarkQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }
    }

    public interface SupportingFeature {
        String getGraphQlTypeName();

        PriorityFeatureType getCategory();
    }

    /**
    * An abstract representation of something which supports a priority prediction. Implementing classes
    * represent specific types of features.
    */
    public static class UnknownSupportingFeature extends AbstractResponse<UnknownSupportingFeature> implements SupportingFeature {
        public UnknownSupportingFeature() {
        }

        public UnknownSupportingFeature(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "category": {
                        PriorityFeatureType optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PriorityFeatureType.fromGraphQl(jsonAsString(field.getValue(), key));
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

        public static SupportingFeature create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "SupportingParticipant": {
                    return new SupportingParticipant(fields);
                }

                case "SupportingPhrase": {
                    return new SupportingPhrase(fields);
                }

                case "SupportingUserMark": {
                    return new SupportingUserMark(fields);
                }

                default: {
                    return new UnknownSupportingFeature(fields);
                }
            }
        }

        public String getGraphQlTypeName() {
            return (String) get("__typename");
        }

        public PriorityFeatureType getCategory() {
            return (PriorityFeatureType) get("category");
        }

        public UnknownSupportingFeature setCategory(PriorityFeatureType arg) {
            optimisticData.put(getKey("category"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "category": return false;

                default: return false;
            }
        }
    }

    public interface SupportingParticipantQueryDefinition {
        void define(SupportingParticipantQuery _queryBuilder);
    }

    /**
    * References a person that was meaningful to the priority prediction
    */
    public static class SupportingParticipantQuery extends Query<SupportingParticipantQuery> {
        SupportingParticipantQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SupportingParticipantQuery person(PersonQueryDefinition queryDef) {
            startField("person");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SupportingParticipantQuery category() {
            startField("category");

            return this;
        }
    }

    /**
    * References a person that was meaningful to the priority prediction
    */
    public static class SupportingParticipant extends AbstractResponse<SupportingParticipant> implements SupportingFeature {
        public SupportingParticipant() {
        }

        public SupportingParticipant(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "person": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "category": {
                        PriorityFeatureType optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PriorityFeatureType.fromGraphQl(jsonAsString(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "SupportingParticipant";
        }

        public Person getPerson() {
            return (Person) get("person");
        }

        public SupportingParticipant setPerson(Person arg) {
            optimisticData.put(getKey("person"), arg);
            return this;
        }

        public PriorityFeatureType getCategory() {
            return (PriorityFeatureType) get("category");
        }

        public SupportingParticipant setCategory(PriorityFeatureType arg) {
            optimisticData.put(getKey("category"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "person": return true;

                case "category": return false;

                default: return false;
            }
        }
    }

    public interface SupportingPhraseQueryDefinition {
        void define(SupportingPhraseQuery _queryBuilder);
    }

    /**
    * References a phrase that was meaningful to the priority decision
    */
    public static class SupportingPhraseQuery extends Query<SupportingPhraseQuery> {
        SupportingPhraseQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SupportingPhraseQuery label() {
            startField("label");

            return this;
        }

        public SupportingPhraseQuery category() {
            startField("category");

            return this;
        }
    }

    /**
    * References a phrase that was meaningful to the priority decision
    */
    public static class SupportingPhrase extends AbstractResponse<SupportingPhrase> implements SupportingFeature {
        public SupportingPhrase() {
        }

        public SupportingPhrase(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "label": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "category": {
                        PriorityFeatureType optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PriorityFeatureType.fromGraphQl(jsonAsString(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "SupportingPhrase";
        }

        public String getLabel() {
            return (String) get("label");
        }

        public SupportingPhrase setLabel(String arg) {
            optimisticData.put(getKey("label"), arg);
            return this;
        }

        public PriorityFeatureType getCategory() {
            return (PriorityFeatureType) get("category");
        }

        public SupportingPhrase setCategory(PriorityFeatureType arg) {
            optimisticData.put(getKey("category"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "label": return false;

                case "category": return false;

                default: return false;
            }
        }
    }

    public interface SupportingUserMarkQueryDefinition {
        void define(SupportingUserMarkQuery _queryBuilder);
    }

    /**
    * Indicates the user has explicitly marked the item as a priority
    */
    public static class SupportingUserMarkQuery extends Query<SupportingUserMarkQuery> {
        SupportingUserMarkQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SupportingUserMarkQuery category() {
            startField("category");

            return this;
        }
    }

    /**
    * Indicates the user has explicitly marked the item as a priority
    */
    public static class SupportingUserMark extends AbstractResponse<SupportingUserMark> implements SupportingFeature {
        public SupportingUserMark() {
        }

        public SupportingUserMark(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "category": {
                        PriorityFeatureType optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PriorityFeatureType.fromGraphQl(jsonAsString(field.getValue(), key));
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

        public String getGraphQlTypeName() {
            return "SupportingUserMark";
        }

        public PriorityFeatureType getCategory() {
            return (PriorityFeatureType) get("category");
        }

        public SupportingUserMark setCategory(PriorityFeatureType arg) {
            optimisticData.put(getKey("category"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "category": return false;

                default: return false;
            }
        }
    }

    public interface TargetedMessageMutationQueryDefinition {
        void define(TargetedMessageMutationQuery _queryBuilder);
    }

    /**
    * [Beta] A mutation object for a targeted message mutation
    */
    public static class TargetedMessageMutationQuery extends Query<TargetedMessageMutationQuery> {
        TargetedMessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TargetedMessageMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    /**
    * [Beta] A mutation object for a targeted message mutation
    */
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

        public String getGraphQlTypeName() {
            return "TargetedMessageMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public TargetedMessageMutation setSuccessful(Boolean arg) {
            optimisticData.put(getKey("successful"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public interface TaxonomyQueryDefinition {
        void define(TaxonomyQuery _queryBuilder);
    }

    /**
    * A single taxonomy object
    */
    public static class TaxonomyQuery extends Query<TaxonomyQuery> {
        TaxonomyQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TaxonomyQuery category() {
            startField("category");

            return this;
        }

        public TaxonomyQuery label() {
            startField("label");

            return this;
        }

        public TaxonomyQuery score() {
            startField("score");

            return this;
        }
    }

    /**
    * A single taxonomy object
    */
    public static class Taxonomy extends AbstractResponse<Taxonomy> implements SummaryPhrase {
        public Taxonomy() {
        }

        public Taxonomy(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "category": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
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

        public String getGraphQlTypeName() {
            return "Taxonomy";
        }

        public String getCategory() {
            return (String) get("category");
        }

        public Taxonomy setCategory(String arg) {
            optimisticData.put(getKey("category"), arg);
            return this;
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Taxonomy setLabel(String arg) {
            optimisticData.put(getKey("label"), arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Taxonomy setScore(Double arg) {
            optimisticData.put(getKey("score"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "category": return false;

                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public interface TeamQueryDefinition {
        void define(TeamQuery _queryBuilder);
    }

    /**
    * A group of people with the same business owner
    */
    public static class TeamQuery extends Query<TeamQuery> {
        TeamQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TeamQuery id() {
            startField("id");

            return this;
        }

        public TeamQuery displayName() {
            startField("displayName");

            return this;
        }

        /**
        * List of Subscriptions that belong to this team
        */
        public TeamQuery subscriptions(SubscriptionQueryDefinition queryDef) {
            startField("subscriptions");

            _queryBuilder.append('{');
            queryDef.define(new SubscriptionQuery(_queryBuilder));
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

            public MembersArguments role(RoleEnum value) {
                if (value != null) {
                    startArgument("role");
                    _queryBuilder.append(value.toString());
                }
                return this;
            }
        }

        public interface MembersArgumentsDefinition {
            void define(MembersArguments args);
        }

        public TeamQuery members(PersonCollectionQueryDefinition queryDef) {
            return members(args -> {}, queryDef);
        }

        public TeamQuery members(MembersArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("members");

            MembersArguments args = new MembersArguments(_queryBuilder);
            argsDef.define(args);
            MembersArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A group of people with the same business owner
    */
    public static class Team extends AbstractResponse<Team> {
        public Team() {
        }

        public Team(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

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

                    case "subscriptions": {
                        List<Subscription> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Subscription optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Subscription(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

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

        public String getGraphQlTypeName() {
            return "Team";
        }

        public ID getId() {
            return (ID) get("id");
        }

        public Team setId(ID arg) {
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public Team setDisplayName(String arg) {
            optimisticData.put(getKey("displayName"), arg);
            return this;
        }

        /**
        * List of Subscriptions that belong to this team
        */

        public List<Subscription> getSubscriptions() {
            return (List<Subscription>) get("subscriptions");
        }

        public Team setSubscriptions(List<Subscription> arg) {
            optimisticData.put(getKey("subscriptions"), arg);
            return this;
        }

        public PersonCollection getMembers() {
            return (PersonCollection) get("members");
        }

        public Team setMembers(PersonCollection arg) {
            optimisticData.put(getKey("members"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "id": return false;

                case "displayName": return false;

                case "subscriptions": return true;

                case "members": return true;

                default: return false;
            }
        }
    }

    public interface TeamCollectionQueryDefinition {
        void define(TeamCollectionQuery _queryBuilder);
    }

    public static class TeamCollectionQuery extends Query<TeamCollectionQuery> {
        TeamCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TeamCollectionQuery items(TeamQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new TeamQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class TeamCollection extends AbstractResponse<TeamCollection> {
        public TeamCollection() {
        }

        public TeamCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "items": {
                        List<Team> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Team optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Team(jsonAsObject(element1, key));
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

        public String getGraphQlTypeName() {
            return "TeamCollection";
        }

        public List<Team> getItems() {
            return (List<Team>) get("items");
        }

        public TeamCollection setItems(List<Team> arg) {
            optimisticData.put(getKey("items"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "items": return true;

                default: return false;
            }
        }
    }

    public interface TeamMutationQueryDefinition {
        void define(TeamMutationQuery _queryBuilder);
    }

    public static class TeamMutationQuery extends Query<TeamMutationQuery> {
        TeamMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TeamMutationQuery teamId() {
            startField("teamId");

            return this;
        }

        public TeamMutationQuery title() {
            startField("title");

            return this;
        }

        public TeamMutationQuery roles(RoleQueryDefinition queryDef) {
            startField("roles");

            _queryBuilder.append('{');
            queryDef.define(new RoleQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public TeamMutationQuery team(TeamQueryDefinition queryDef) {
            startField("team");

            _queryBuilder.append('{');
            queryDef.define(new TeamQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class TeamMutation extends AbstractResponse<TeamMutation> {
        public TeamMutation() {
        }

        public TeamMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "teamId": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "title": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "roles": {
                        List<Role> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<Role> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                Role optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = new Role(jsonAsObject(element1, key));
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "team": {
                        responseData.put(key, new Team(jsonAsObject(field.getValue(), key)));

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

        public String getGraphQlTypeName() {
            return "TeamMutation";
        }

        public String getTeamId() {
            return (String) get("teamId");
        }

        public TeamMutation setTeamId(String arg) {
            optimisticData.put(getKey("teamId"), arg);
            return this;
        }

        public String getTitle() {
            return (String) get("title");
        }

        public TeamMutation setTitle(String arg) {
            optimisticData.put(getKey("title"), arg);
            return this;
        }

        public List<Role> getRoles() {
            return (List<Role>) get("roles");
        }

        public TeamMutation setRoles(List<Role> arg) {
            optimisticData.put(getKey("roles"), arg);
            return this;
        }

        public Team getTeam() {
            return (Team) get("team");
        }

        public TeamMutation setTeam(Team arg) {
            optimisticData.put(getKey("team"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "teamId": return false;

                case "title": return false;

                case "roles": return true;

                case "team": return true;

                default: return false;
            }
        }
    }

    public static class TeamMutationInput implements Serializable {
        private String teamId;

        private Input<String> title = Input.undefined();

        private Input<List<RoleInput>> roles = Input.undefined();

        public TeamMutationInput(String teamId) {
            this.teamId = teamId;
        }

        public String getTeamId() {
            return teamId;
        }

        public TeamMutationInput setTeamId(String teamId) {
            this.teamId = teamId;
            return this;
        }

        public String getTitle() {
            return title.getValue();
        }

        public Input<String> getTitleInput() {
            return title;
        }

        public TeamMutationInput setTitle(String title) {
            this.title = Input.optional(title);
            return this;
        }

        public TeamMutationInput setTitleInput(Input<String> title) {
            if (title == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.title = title;
            return this;
        }

        public List<RoleInput> getRoles() {
            return roles.getValue();
        }

        public Input<List<RoleInput>> getRolesInput() {
            return roles;
        }

        public TeamMutationInput setRoles(List<RoleInput> roles) {
            this.roles = Input.optional(roles);
            return this;
        }

        public TeamMutationInput setRolesInput(Input<List<RoleInput>> roles) {
            if (roles == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.roles = roles;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("teamId:");
            Query.appendQuotedString(_queryBuilder, teamId.toString());

            if (this.title.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                if (title.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, title.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.roles.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("roles:");
                if (roles.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (RoleInput item1 : roles.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        item1.appendTo(_queryBuilder);
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    public static class UpdateSpaceInput implements Serializable {
        private String id;

        private Input<String> title = Input.undefined();

        private Input<List<String>> members = Input.undefined();

        private Input<MemberOperation> memberOperation = Input.undefined();

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
            return title.getValue();
        }

        public Input<String> getTitleInput() {
            return title;
        }

        public UpdateSpaceInput setTitle(String title) {
            this.title = Input.optional(title);
            return this;
        }

        public UpdateSpaceInput setTitleInput(Input<String> title) {
            if (title == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.title = title;
            return this;
        }

        public List<String> getMembers() {
            return members.getValue();
        }

        public Input<List<String>> getMembersInput() {
            return members;
        }

        public UpdateSpaceInput setMembers(List<String> members) {
            this.members = Input.optional(members);
            return this;
        }

        public UpdateSpaceInput setMembersInput(Input<List<String>> members) {
            if (members == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
            this.members = members;
            return this;
        }

        public MemberOperation getMemberOperation() {
            return memberOperation.getValue();
        }

        public Input<MemberOperation> getMemberOperationInput() {
            return memberOperation;
        }

        public UpdateSpaceInput setMemberOperation(MemberOperation memberOperation) {
            this.memberOperation = Input.optional(memberOperation);
            return this;
        }

        public UpdateSpaceInput setMemberOperationInput(Input<MemberOperation> memberOperation) {
            if (memberOperation == null) {
                throw new IllegalArgumentException("Input can not be null");
            }
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

            if (this.title.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                if (title.getValue() != null) {
                    Query.appendQuotedString(_queryBuilder, title.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.members.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                if (members.getValue() != null) {
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : members.getValue()) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1.toString());
                    }
                    _queryBuilder.append(']');
                } else {
                    _queryBuilder.append("null");
                }
            }

            if (this.memberOperation.isDefined()) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("memberOperation:");
                if (memberOperation.getValue() != null) {
                    _queryBuilder.append(memberOperation.getValue().toString());
                } else {
                    _queryBuilder.append("null");
                }
            }

            _queryBuilder.append('}');
        }
    }

    public interface UserPriorityStatusQueryDefinition {
        void define(UserPriorityStatusQuery _queryBuilder);
    }

    /**
    * A single UserPriorityStatus object indicates moment's priority for user
    */
    public static class UserPriorityStatusQuery extends Query<UserPriorityStatusQuery> {
        UserPriorityStatusQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public UserPriorityStatusQuery predicted() {
            startField("predicted");

            return this;
        }

        public class SupportArguments extends Arguments {
            SupportArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public SupportArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SupportArgumentsDefinition {
            void define(SupportArguments args);
        }

        /**
        * Features which support the prediction made in this UserPriorityStatus. Each SupportingFeature is a
        * personalized reason the system predicted the priority as true (a priority) or false (not a
        * priority).
        */
        public UserPriorityStatusQuery support(SupportingFeatureQueryDefinition queryDef) {
            return support(args -> {}, queryDef);
        }

        /**
        * Features which support the prediction made in this UserPriorityStatus. Each SupportingFeature is a
        * personalized reason the system predicted the priority as true (a priority) or false (not a
        * priority).
        */
        public UserPriorityStatusQuery support(SupportArgumentsDefinition argsDef, SupportingFeatureQueryDefinition queryDef) {
            startField("support");

            SupportArguments args = new SupportArguments(_queryBuilder);
            argsDef.define(args);
            SupportArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new SupportingFeatureQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    /**
    * A single UserPriorityStatus object indicates moment's priority for user
    */
    public static class UserPriorityStatus extends AbstractResponse<UserPriorityStatus> {
        public UserPriorityStatus() {
        }

        public UserPriorityStatus(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "predicted": {
                        Boolean optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsBoolean(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "support": {
                        List<SupportingFeature> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<SupportingFeature> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                SupportingFeature optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = UnknownSupportingFeature.create(jsonAsObject(element1, key));
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

        public String getGraphQlTypeName() {
            return "UserPriorityStatus";
        }

        public Boolean getPredicted() {
            return (Boolean) get("predicted");
        }

        public UserPriorityStatus setPredicted(Boolean arg) {
            optimisticData.put(getKey("predicted"), arg);
            return this;
        }

        /**
        * Features which support the prediction made in this UserPriorityStatus. Each SupportingFeature is a
        * personalized reason the system predicted the priority as true (a priority) or false (not a
        * priority).
        */

        public List<SupportingFeature> getSupport() {
            return (List<SupportingFeature>) get("support");
        }

        public UserPriorityStatus setSupport(List<SupportingFeature> arg) {
            optimisticData.put(getKey("support"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "predicted": return false;

                case "support": return false;

                default: return false;
            }
        }
    }
}
