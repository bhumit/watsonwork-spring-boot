package com.ibm.watsonwork.model.jira;

import lombok.Data;

@Data
public class User {

    private String self;
    private String key;
    private String accountId;
    private String name;
    private String emailAddress;
    private String displayName;
    private Boolean active;
    private String timeZone;
    private String locale;
}
