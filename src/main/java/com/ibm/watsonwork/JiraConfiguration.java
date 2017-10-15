package com.ibm.watsonwork;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.ProjectRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JiraConfiguration {


    @Autowired
    private JiraApiProperties jiraApiProperties;

    @Bean
    public JiraRestClient jiraRestClient() {
        JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
        AuthenticationHandler authenticationHandler = new BasicHttpAuthenticationHandler(jiraApiProperties.getApiUsername(), jiraApiProperties.getApiPassword());
        JiraRestClient jiraRestClient = null;
        try {
            jiraRestClient = restClientFactory.create(new URI(jiraApiProperties.getApiUrl()), authenticationHandler);
        } catch (URISyntaxException e) {
            log.error("Failed to initialize rest client factory", e);
        }

        return jiraRestClient;
    }

    @Bean
    public IssueRestClient issueRestClient() {
        return jiraRestClient().getIssueClient();
    }

    @Bean
    public UserRestClient userRestClient() {
        return jiraRestClient().getUserClient();
    }

    @Bean
    public ProjectRestClient projectRestClient() {
        return jiraRestClient().getProjectClient();
    }

    @Bean
    public SearchRestClient searchRestClient() {
        return jiraRestClient().getSearchClient();
    }


}
