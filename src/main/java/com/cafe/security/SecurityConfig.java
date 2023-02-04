package com.cafe.security;

import com.cafe.controller.JwtTokenValidationFilter;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public SecurityConfig(JwtService jwtService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserService userService, UserRoleService userRoleService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtService, userService, userRoleService);
        JwtAuthorizationFilter authorizationFilter = new JwtAuthorizationFilter(jwtService, userService, userRoleService);
        JwtTokenValidationFilter jwtTokenValidationFilter = new JwtTokenValidationFilter(jwtService);
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtTokenValidationFilter, JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/refresh-token").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/users/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/products").hasAnyAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/products/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.PUT, "/products/update/*").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/tables/register").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/tables-to-waiters").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST, "/tables-to-waiters/assign").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.GET, "/tables").hasAnyAuthority("MANAGER", "WAITER")
                .antMatchers(HttpMethod.DELETE, "/tables/*").hasAuthority("MANAGER")
                .antMatchers(HttpMethod.POST, "/orders/register/*").hasAuthority("WAITER")
                .antMatchers(HttpMethod.POST, "/products-in-order/register/*").hasAuthority("WAITER")
                .antMatchers(HttpMethod.GET, "/products-in-order/*").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT, "/products-in-order/update/*").hasAuthority("WAITER")
                .antMatchers(HttpMethod.PUT, "/orders/update/*").hasAuthority("WAITER")
                .anyRequest()
                .authenticated();
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtService, userService, userRoleService))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtService, userService, userRoleService))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        //corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin");
        //corsConfiguration.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}


