package com.testco.feothtenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class VerifyService {

    final static Logger LOGGER = LoggerFactory.getLogger(VerifyService.class);

    private final WebClient webclient;

    @Value("${api.provider.intune.base-uri}")
    String intuneBaseUri;

    @Value("${api.provider.intune.endpoint}")
    String intuneEndpoint;

    public VerifyService(WebClient webclient) {
        this.webclient = webclient;
    }

    public void verify(OAuth2AuthorizedClient authorizedClient) {
        authorizedClient.getAccessToken();
        LOGGER.info("Verifying access.");
        try {
            webclient
                    .get()
                    .uri(new URI(intuneBaseUri + intuneEndpoint))
                    .attributes(oauth2AuthorizedClient(authorizedClient))
//                    .attributes(clientRegistrationId("testco-webapp"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(authorizedClient.getAccessToken().getTokenValue());

        } catch (Exception e) {
            LOGGER.error("Verification failed.");
            throw new VerificationException("Error occurred calling back-end URI.", e);
        }


    }
}
