package com.carsite.config;

import com.carsite.security.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    TokenParser tokenParser;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors().and().headers().xssProtection().disable().and()
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET,"makes").permitAll()
                        .requestMatchers(HttpMethod.GET,"make/{name}").permitAll()
                        .requestMatchers(HttpMethod.GET,"models/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"trimbycarmodel/{id}/{model}").permitAll()
                        .requestMatchers(HttpMethod.GET,"trimbycar/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"trims/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET,"ad").permitAll()
                        .requestMatchers(HttpMethod.GET,"ad/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"ad").permitAll()
                        .requestMatchers(HttpMethod.PUT,"ad/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"ad/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET,"ad/{adId}/images").permitAll()
                        .requestMatchers(HttpMethod.GET,"images/{filename:.+}").permitAll()
                        .requestMatchers(HttpMethod.GET,"images").permitAll()
                        .requestMatchers(HttpMethod.GET,"images/").permitAll()
                        .requestMatchers(HttpMethod.POST,"upload").permitAll()


                        .requestMatchers(HttpMethod.GET,"account").permitAll()
                        .requestMatchers(HttpMethod.GET,"user-account").permitAll()
                        .requestMatchers(HttpMethod.GET,"account/{id}/ads").permitAll()
                        .requestMatchers("signup").permitAll()
                        .requestMatchers("login").permitAll()


                        .anyRequest()
                        .authenticated())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenParser, BasicAuthenticationFilter.class)
                .build();
    }
}
