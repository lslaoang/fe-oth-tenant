package com.testco.feothtenant.controller;

import com.testco.feothtenant.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurePageController {

    @Autowired
    VerifyService verifyService;

    @RequestMapping("/secure_page")
    public ModelAndView securePage(){
        verifyService.verify();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mav = new ModelAndView("secure_page");

        return mav;
    }

    @RequestMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");

        return mav;
    }
}
