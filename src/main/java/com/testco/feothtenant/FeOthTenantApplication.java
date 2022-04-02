package com.testco.feothtenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FeOthTenantApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FeOthTenantApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FeOthTenantApplication.class);
    }

}
