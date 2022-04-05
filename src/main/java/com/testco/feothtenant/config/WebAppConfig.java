package com.testco.feothtenant.config;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppConfig extends AADWebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/secure_page").authenticated()
                .antMatchers("/**").permitAll();
        // @formatter:on
    }

}
