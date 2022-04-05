package com.testco.feothtenant.controller;

import com.testco.feothtenant.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurePageController {

    @Autowired
    VerifyService verifyService;

    @RequestMapping("/secure_page")
    public ModelAndView securePage() {
        return new ModelAndView("secure_page");
    }

    @RequestMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/verify")
    public ModelAndView verifyMe(@RegisteredOAuth2AuthorizedClient("testco-webapp")
                                         OAuth2AuthorizedClient authorizedClient) {
        verifyService.verify((authorizedClient));
        return new ModelAndView("welcome");

    }

}
