package com.ibm.watsonwork.client;

import java.util.List;

import com.ibm.watsonwork.model.jira.Name;
import com.ibm.watsonwork.model.jira.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JiraClient {

    @Headers({"Content-Type: application/json"})
    @GET("/rest/api/latest/user/assignable/search")
    Call<List<User>> getAssignableUserForIssue(@Header("Authorization") String basicAuthorization, @Query(value = "issueKey") String issueKey);

    @Headers({"Content-Type: application/json"})
    @PUT("/rest/api/2/issue/{issueIdOrKey}/assignee")
    Call<String> assignIssue(@Header("Authorization") String basicAuthorization,
                                                    @Path("issueIdOrKey") String issueIdOrKey,
                                                    @Body Name name);
}
