package com.testco.feothtenant.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurePageController {

    @RequestMapping("/secure_page")
    public ModelAndView securePage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mav = new ModelAndView("secure_page");

        return mav;
    }

    @RequestMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");

        return mav;
    }
}
