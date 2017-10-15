package com.ibm.watsonwork.service;

import java.io.IOException;
import java.util.List;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ibm.watsonwork.model.jira.User;

public interface JiraService extends Service {

    List<Issue> getUnassignedByPriority(String priority);

    List<Issue> getAssignedByPriority(String priority);

    List<Issue> getAllByPriority(String priority);

    List<Issue> searchIssuesByKeywords(String keyword);

    List<User> getAssignableUsers(String issueKey) throws IOException;

    String assignIssue(String userKey, String issueKey) throws IOException;

    List<Issue> getAssignedToUser(String user);


}
