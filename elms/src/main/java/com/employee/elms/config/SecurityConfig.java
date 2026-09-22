package com.employee.elms.config;

import com.employee.elms.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter)throws Exception{

        httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/elms-frontend",
                                "/index.html",
                                "/login.html",
                                "/dashboard.html",
                                "/elms-frontend/css/**",
                                "/elms-frontend/js/**",
                                "/elms-frontend/assets/**",
                                "/favicon.ico"
                        ).permitAll()

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
                        ).hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/leave/{id}"
                        ).hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/employee",
                                "/employee/**"
                        ).hasAnyRole("ADMIN", "MANAGER")

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
                .httpBasic(http -> http.disable())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

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
