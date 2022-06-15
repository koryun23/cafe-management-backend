package com.cafe.security;

import com.cafe.service.core.user.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/users/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/products/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.PUT, "/products/update").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/tables/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/tables").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST, "/TablesWaiters").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/orders/register").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST, "/ProductInOrder/register").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT, "/ProductInOrder/update").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT, "/orders/update").hasAuthority("WAITER")
                .anyRequest()
                .authenticated();
        http.httpBasic();
    }
}
