package com.kpu.stocktrading.oose.stock_management.config;

import com.kpu.stocktrading.oose.stock_management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/*
Configuration for Spring Security
Setup for login, logout, and other security.
*/
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserService userService;
    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    //Setting up connection with the authentication service, and user details entered.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Login logout redirecting and setup
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Disable security for CSRF attacks (testing)
                .csrf(AbstractHttpConfigurer::disable)
                //Pre-authenticating paths
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/signup", "/h2-console").permitAll()
                        .anyRequest().authenticated()
                )
                //Login path, and routing.
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticate/login")
                        .defaultSuccessUrl("/authenticate/login/redirect")
                        .permitAll()
                )
                //Logout path, and routing
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .build();
    }

    @Bean
    //Password Encoder
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}