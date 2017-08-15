package com.ibm.watsonwork;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ibm.watsonwork.client.AuthClient;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.client.WatsonWorkClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class WatsonWorkConfiguration {

    @Autowired
    private WatsonWorkProperties watsonWorkProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(9, TimeUnit.SECONDS).build();
    }

    @Bean
    @Primary
    public Retrofit retrofit(OkHttpClient client) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(watsonWorkProperties.getApiUri())
                .client(client)
                .build();
    }

    @Bean
    public WatsonWorkClient watsonWorkClient(Retrofit retrofit) {
        return retrofit.create(WatsonWorkClient.class);
    }

    @Bean
    public AuthClient authClient(Retrofit retrofit) {
        return retrofit.create(AuthClient.class);
    }

    @Bean
    public GraphQLClient graphQLClient(Retrofit retrofit) {
        return retrofit.create(GraphQLClient.class);
    }

}
