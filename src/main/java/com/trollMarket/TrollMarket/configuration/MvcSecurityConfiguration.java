package com.trollMarket.TrollMarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/resources/**","/account/**","/").permitAll()
////                .requestMatchers("/").hasAnyAuthority("Seller","Buyer","Admin")
//                .requestMatchers("/profile/**").hasAnyAuthority("Seller","Buyer")
//                .requestMatchers("/shipment/**", "/history/**").hasAuthority("Admin")
//                .requestMatchers("/merchandise/**").hasAuthority("Seller")
//                .requestMatchers("/shop/**", "/chart/**").hasAuthority("Buyer")
                .anyRequest().authenticated()
        ).formLogin((form)-> form
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/login")
                .failureUrl("/account/faillogin")
        ).rememberMe((context) -> context
                .key("indocyber")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(86400)
        ).logout((logout) -> logout
            .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me-cookie").permitAll()

        ).exceptionHandling((exception)-> exception
                .accessDeniedPage("/account/accessDenied")

        );
        return http.build();
    }
}
