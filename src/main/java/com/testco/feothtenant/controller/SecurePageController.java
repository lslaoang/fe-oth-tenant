package com.testco.feothtenant.controller;

import com.testco.feothtenant.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurePageController {

    @Autowired
    VerifyService verifyService;

    @RequestMapping("/secure_page")
    public ModelAndView securePage() {
        verifyService.verify();
        return new ModelAndView("secure_page");
    }

    @RequestMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }
}
