package com.ibm.watsonwork.service.impl;

import com.ibm.watsonwork.NewsApiProperties;
import com.ibm.watsonwork.client.NewsClient;
import com.ibm.watsonwork.model.NewsResponse;
import com.ibm.watsonwork.service.NewsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
@Slf4j
public class DefaultNewsService implements NewsService {

    @Autowired
    private NewsClient newsClient;

    @Autowired
    private NewsApiProperties newsApiProperties;

    @Override
    @SneakyThrows
    public NewsResponse getLatestNews(String source, String sortBy) {

        Response<NewsResponse> response = newsClient.getArticles(newsApiProperties.getApiSecret(), source, sortBy).execute();
        log.info("getLatestNews for {} by {}", source, sortBy);
        if (response.isSuccessful()) {
            log.info("successfully retrieved articles for news api");
            return response.body();
        }
        if (response.body() == null || response.body().getCode().equals("sourceUnavailableSortedBy")) {
            response = newsClient.getArticles(newsApiProperties.getApiSecret(), source, "").execute();
            log.info("successfully retrieved articles for news api using backup values");
        }

        return response.body();
    }
}
