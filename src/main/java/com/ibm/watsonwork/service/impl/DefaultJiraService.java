package com.ibm.watsonwork.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;
import com.google.common.collect.Lists;
import com.ibm.watsonwork.JiraApiProperties;
import com.ibm.watsonwork.JiraConfiguration;
import com.ibm.watsonwork.WatsonWorkConstants;
import com.ibm.watsonwork.client.JiraClient;
import com.ibm.watsonwork.model.jira.Name;
import com.ibm.watsonwork.model.jira.User;
import com.ibm.watsonwork.service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultJiraService implements JiraService {

    @Autowired
    private JiraConfiguration jiraConfiguration;

    @Autowired
    private JiraClient jiraClient;

    @Autowired
    private JiraApiProperties jiraApiProperties;


    public List<Issue> getUnassignedByPriority(String priority) {
        Promise<SearchResult> searchResultPromise = jiraConfiguration.searchRestClient()
                .searchJql(String.format("priority = %s AND assignee in (EMPTY) order by lastViewed DESC", priority));
        return Lists.newArrayList(searchResultPromise.claim().getIssues());
    }

    public List<Issue> getAssignedByPriority(String priority) {
        Promise<SearchResult> searchResultPromise = jiraConfiguration.searchRestClient()
                .searchJql(String.format("priority = %s AND NOT assignee in (EMPTY) order by lastViewed DESC", priority));
        return Lists.newArrayList(searchResultPromise.claim().getIssues());
    }

    @Override
    public List<Issue> getAllByPriority(String priority) {
        Promise<SearchResult> searchResultPromise = jiraConfiguration.searchRestClient()
                .searchJql(String.format("priority = %s order by lastViewed DESC", priority), 20, null, null);
        return Lists.newArrayList(searchResultPromise.claim().getIssues());
    }

    public List<Issue> getAssignedToUser(String user) {
        Promise<SearchResult> searchResultPromise = jiraConfiguration.searchRestClient()
                .searchJql(String.format("assignee = %s order by priority", user));
        return Lists.newArrayList(searchResultPromise.claim().getIssues());
    }

    @Override
    public List<Issue> searchIssuesByKeywords(String keyword) {
        Promise<SearchResult> searchResultPromise = jiraConfiguration.searchRestClient().searchJql(String.format("summary ~ %s OR description ~ %s ORDER BY lastViewed DESC", keyword, keyword));
        return Lists.newArrayList(searchResultPromise.claim().getIssues());
    }


    public List<User> getAssignableUsers(String issueKey) throws IOException {
        return jiraClient.getAssignableUserForIssue(createAppAuthHeader(), issueKey).execute().body();
    }

    @Override
    public String assignIssue(String userKey, String issueKey) throws IOException {
        Name name = new Name();
        name.setName(userKey);
        return jiraClient.assignIssue(createAppAuthHeader(), issueKey, name).execute().body();
    }

    private String createAppAuthHeader() {
        return WatsonWorkConstants.BASIC + Base64.getEncoder().encodeToString((jiraApiProperties.getApiUsername() + ":" + jiraApiProperties.getApiPassword()).getBytes());
    }

}
