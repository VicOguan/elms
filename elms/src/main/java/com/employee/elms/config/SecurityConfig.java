package com.employee.elms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/register",
                                "/auth/login",
                                "/auth/register/manager"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/leave"
                        ).hasRole("EMPLOYEE")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/leave/my"
                        ).hasRole("EMPLOYEE")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/leave"
                        ).hasAnyRole("ADMIN","MANAGER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/leave/{id}/status"
                        ).hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/leave/{id}"
                        ).hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/employee",
                                "/employee/**"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/employee"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/employee/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/employee/**"
                        ).hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
