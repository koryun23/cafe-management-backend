package com.cafe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users").hasRole("MANAGER")
                .antMatchers("/products").hasRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/tables").hasRole("MANAGER")
                .antMatchers("TablesWaiters").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/tables").hasRole("WAITER")
                .antMatchers("/orders").hasRole("WAITER")
                .antMatchers("/ProductInOrder").hasRole("WAITER")
                .anyRequest()
                .authenticated();
        http.httpBasic();
    }
}
