package com.ibm.watsonwork.utils;

import java.io.IOException;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watsonwork.MessageTypes;
import com.ibm.watsonwork.model.Annotation;
import com.ibm.watsonwork.model.AnnotationPayload;
import com.ibm.watsonwork.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Message buildMessage(String messageTitle, String messageText) {
        Annotation annotation = new Annotation();
        annotation.setType(MessageTypes.GENERIC_ANNOTATION);
        annotation.setVersion(1.0);
        annotation.setColor("#1DB954");
        annotation.setTitle(messageTitle);
        annotation.setText(messageText);

//        Actor actor = new Actor();
//        actor.setUrl("");
//        actor.setAvatar("");
//        actor.setName("");
//        annotation.setActor(actor);

        Message message = new Message();
        message.setType(MessageTypes.APP_MESSAGE);
        message.setVersion(1.0);
        message.setAnnotations(Collections.singletonList(annotation));

        return message;
    }

    public static Message buildMessage(Annotation annotation) {
        Message message = new Message();
        message.setType(MessageTypes.APP_MESSAGE);
        message.setVersion(1.0);
        message.setAnnotations(Collections.singletonList(annotation));

        return message;
    }

    public static AnnotationPayload mapAnnotationPayload(String annotationPayload) {
        AnnotationPayload payload = null;
        try {
            payload = objectMapper.readValue(annotationPayload, AnnotationPayload.class);
        } catch (IOException e) {
            log.error("Failed to read the annotation", e);
        }
        return payload;
    }
}
