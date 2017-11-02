package com.ibm.watsonwork.service;


import java.io.IOException;

import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.schema.WatsonWorkSchema.Message;

public interface GraphQLService extends Service {

    void processWebhookEvent(WebhookEvent event) throws IOException;

    Message getMessage(String messageId);


}
