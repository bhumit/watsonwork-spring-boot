package com.ibm.watsonwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zendesk.client.v2.Zendesk;

@Configuration
public class ZendeskConfiguration {

    @Autowired
    public ZendeskApiProperties zendeskApiProperties;

    @Bean
    public Zendesk zendesk() {
        return new Zendesk.Builder(zendeskApiProperties.getUrl())
                .setUsername(zendeskApiProperties.getUsername())
                .setToken(zendeskApiProperties.getToken())
                .build();
    }
}
