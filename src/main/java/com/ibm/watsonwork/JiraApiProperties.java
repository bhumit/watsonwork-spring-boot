package com.ibm.watsonwork;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class JiraApiProperties {

    @Value("${jira.api.uri}")
    private String apiUrl;

    @Value("${jira.api.username}")
    private String apiUsername;

    @Value("${jira.api.password}")
    private String apiPassword;
}
