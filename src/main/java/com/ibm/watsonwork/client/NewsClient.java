package com.ibm.watsonwork.client;

import com.ibm.watsonwork.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsClient {

    @Headers({"Content-Type: application/json"})
    @GET("/v1/articles")
    Call<NewsResponse> getArticles(@Query("apiKey") String apiKey,
                                   @Query("source") String source,
                                   @Query("sortBy") String sortBy);

}
