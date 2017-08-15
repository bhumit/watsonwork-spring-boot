package com.ibm.watsonwork.model.zendesk;

import lombok.Data;

@Data
public class TicketInfo {

    private String id;
    private String title;
    private String url;
    private String description;
    private String assignedTo;
    private String comment;
    private Status status;
}
