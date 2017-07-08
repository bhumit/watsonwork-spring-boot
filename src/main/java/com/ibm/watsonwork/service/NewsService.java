package com.ibm.watsonwork.service;

import com.ibm.watsonwork.model.NewsResponse;

public interface NewsService {

    NewsResponse getLatestNews(String source, String sortBy);

}
