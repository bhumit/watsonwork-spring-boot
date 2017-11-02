package com.ibm.watsonwork.controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.ibm.watsonwork.WatsonWorkProperties;
import com.ibm.watsonwork.model.WebhookEvent;
import com.ibm.watsonwork.service.AuthService;
import com.ibm.watsonwork.service.GraphQLService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.ibm.watsonwork.MessageTypes.MESSAGE_CREATED;
import static com.ibm.watsonwork.MessageTypes.VERIFICATION;
import static com.ibm.watsonwork.WatsonWorkConstants.X_OUTBOUND_TOKEN;

@Controller
@Slf4j
public class WatsonWorkController {

    @Autowired
    private WatsonWorkProperties watsonWorkProperties;

    @Autowired
    private AuthService authService;

    @Autowired
    private GraphQLService graphQLService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/webhook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity webhookCallback(@RequestHeader(X_OUTBOUND_TOKEN) String outboundToken, @RequestBody WebhookEvent webhookEvent) throws IOException {

        if (VERIFICATION.equalsIgnoreCase(webhookEvent.getType()) && authService.isValidVerificationRequest(webhookEvent, outboundToken)) {
            log.info("building verification response...");
            return buildVerificationResponse(webhookEvent);
        }
        processWebhook(webhookEvent);
        return ResponseEntity.ok().build();
    }

    private void processWebhook(WebhookEvent webhookEvent) {
        log.info("processing webhook event...");
        if (StringUtils.equals(watsonWorkProperties.getAppId(), webhookEvent.getUserId())) {
            log.info("ignoring self messages...");
            return;
        }

        if (MESSAGE_CREATED.equalsIgnoreCase(webhookEvent.getType())) {

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                graphQLService.processWebhookEvent(webhookEvent);
                            } catch (IOException e) {
                                log.error("processing webhook failed..");
                            }
                        }
                    },
                    3000
            );


        }
    }

    private ResponseEntity buildVerificationResponse(WebhookEvent webhookEvent) {
        String responseBody = String.format("{\"response\": \"%s\"}", webhookEvent.getChallenge());

        String verificationHeader = authService.createVerificationHeader(responseBody);
        log.info("webhook verified...");
        return ResponseEntity.status(HttpStatus.OK)
                .header(X_OUTBOUND_TOKEN, verificationHeader)
                .body(responseBody);
    }
}
