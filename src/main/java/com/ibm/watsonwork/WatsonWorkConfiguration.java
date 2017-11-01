package com.ibm.watsonwork;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ibm.watsonwork.client.AuthClient;
import com.ibm.watsonwork.client.GraphQLClient;
import com.ibm.watsonwork.client.JiraClient;
import com.ibm.watsonwork.client.WatsonWorkClient;
import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import com.jcabi.http.wire.AutoRedirectingWire;
import com.jcabi.manifests.Manifests;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private JiraApiProperties jiraApiProperties;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    /**
     * Version of us.
     */
    private static final String USER_AGENT = String.format(
            "jcabi-github %s %s %s",
            Manifests.read("JCabi-Version"),
            Manifests.read("JCabi-Build"),
            Manifests.read("JCabi-Date")
    );

    private static final Request REQUEST =
            new ApacheRequest("https://github.ibm.com/api/v3/")
//                    .header(HttpHeaders.USER_AGENT, USER_AGENT)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                    .header(HttpHeaders.AUTHORIZATION, String.format(
//                            "Basic %s",
//                            DatatypeConverter.printBase64Binary(
//                                    String.format("%s:%s", "bhumitpatel@ie.ibm.com", "7e80d70caab521f982a5fcd2ae82ba297fa0b9ed")
//                                            .getBytes(StandardCharsets.UTF_8)
//                            )
    .header(
            HttpHeaders.AUTHORIZATION,
            String.format("token %s", "7e80d70caab521f982a5fcd2ae82ba297fa0b9ed")
                    ).through(AutoRedirectingWire.class);


    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
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

    @Bean(name = "jiraApi")
    public Retrofit retrofitJiraClient(OkHttpClient client) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(jiraApiProperties.getApiUrl())
                .client(client)
                .build();
    }

    @Bean
    public Github github() {
        Github github = new RtGithub(REQUEST);
        return github;
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
    public JiraClient newsClient(@Qualifier(value = "jiraApi") Retrofit retrofit) {
        return retrofit.create(JiraClient.class);
    }

    @Bean
    public GraphQLClient graphQLClient(Retrofit retrofit) {
        return retrofit.create(GraphQLClient.class);
    }

}
