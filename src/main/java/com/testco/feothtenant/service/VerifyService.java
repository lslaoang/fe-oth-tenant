package com.testco.feothtenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class VerifyService {

    final static Logger LOGGER = LoggerFactory.getLogger(VerifyService.class);

    private final WebClient webclient;

    @Value("${api.backend.verify}")
    String backEndLink;

    public VerifyService(WebClient webclient) {
        this.webclient = webclient;
    }

    private static String getToken() {
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        return token;
    }
    public void verify() {
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
                    .headers(httpHeaders -> httpHeaders.setBearerAuth(getToken()))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            LOGGER.error("Verification failed.");
            throw new VerificationException("Error occurred calling back-end URI.", e);
        }
    }
}
