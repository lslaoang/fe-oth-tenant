package com.testco.feothtenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class VerifyService {

    final static Logger LOGGER = LoggerFactory.getLogger(VerifyService.class);

    private final WebClient webclient;

    @Value("${api.backend.verify}")
    String backEndLink;

    public VerifyService(WebClient webclient) {
        this.webclient = webclient;
    }

    public void verify(OAuth2AuthorizedClient authorizedClient) {
        LOGGER.info("Verifying access.");
        URI uri;
        try {
            uri = new URI(backEndLink);
        } catch (URISyntaxException e) {
            throw new VerificationException("Unable to create URI. {}", e);
        }

        try {
            webclient
                    .get()
                    .uri(uri)
                    .attributes(oauth2AuthorizedClient(authorizedClient))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            LOGGER.error("Verification failed.");
            throw new VerificationException("Error occurred calling back-end URI.", e);
        }
    }
}
