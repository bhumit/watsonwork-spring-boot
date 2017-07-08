package com.ibm.watsonwork.client;

import com.ibm.watsonwork.model.GraphQLQuery;
import com.ibm.watsonwork.schema.WatsonWorkSchema.MutationResponse;
import com.ibm.watsonwork.schema.WatsonWorkSchema.QueryResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GraphQLClient {

    String CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json";
    String GRAPHQL_ACTIONS_VIEW = "x-graphql-view: ACTIONS,PUBLIC";
    String GRAPHQL_PUBLIC_VIEW = "x-graphql-view: PUBLIC";
    String GRAPHQL_BETA_VIEW = "x-graphql-view: PUBLIC,BETA";
    String GRAPHQL_PATH = "/graphql";

    @Headers({CONTENT_TYPE_APPLICATION_JSON, GRAPHQL_BETA_VIEW})
    @POST(GRAPHQL_PATH)
    Call<String> createMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);


    @Headers({CONTENT_TYPE_APPLICATION_JSON, GRAPHQL_BETA_VIEW})
    @POST(GRAPHQL_PATH)
    Call<MutationResponse> createTargetedMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);


    @Headers({CONTENT_TYPE_APPLICATION_JSON, GRAPHQL_BETA_VIEW})
    @POST(GRAPHQL_PATH)
    Call<QueryResponse> getMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, GRAPHQL_BETA_VIEW})
    @POST(GRAPHQL_PATH)
    Call<MutationResponse> addMessageFocus(@Header("Authorization") String authToken, @Body GraphQLQuery query);

}
