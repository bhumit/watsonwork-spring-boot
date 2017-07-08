package com.ibm.watsonwork;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class NewsApiProperties {

    @Value("${news.api.uri}")
    private String apiUrl;

    @Value("${news.api.secret}")
    private String apiSecret;
}
