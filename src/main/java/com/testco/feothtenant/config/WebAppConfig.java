package com.testco.feothtenant.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@Order(value = 0)
public class WebAppConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .deleteCookies()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=https%3A%2F%2Flocalhost%3A8080");
    }

}
