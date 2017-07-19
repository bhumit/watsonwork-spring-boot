package com.ibm.watsonwork.service;


import java.util.concurrent.CompletableFuture;

import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.schema.WatsonWorkSchema.CreateTargetedMessageInput;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;
import com.ibm.watsonwork.schema.WatsonWorkSchema.MessageMutation;
import com.ibm.watsonwork.schema.WatsonWorkSchema.TargetedMessageMutation;

public interface GraphQLService extends Service {

    CompletableFuture<TargetedMessageMutation> sendTargetedMessage(WebhookEvent event);

    CompletableFuture<TargetedMessageMutation> respondToActionTrigger(WebhookEvent event);

    Message getMessage(String messageId);

    MessageMutation addMessageFocus(String messageId);

}
