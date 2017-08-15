package com.ibm.watsonwork.model;


import lombok.Data;

@Data
public class AnnotationPayload {

    // actionSelected payload
    private String type;
    private String annotationId;
    private String version;
    private String created;
    private String createdBy;
    private String updated;
    private String updatedBy;
    private String tokenClientId;
    private String conversationId;
    private String targetDialogId;
    private String referralMessageId;
    private String actionId;
    private String targetAppId;

    private String lens;
    private ExtractedInfoResponse extractedInfo;
    private String applicationId;

}