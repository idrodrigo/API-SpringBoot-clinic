package com.rho.cli.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("securityFilterChain");
        return httpSecurity.csrf(csrf->csrf.disable())
                .sessionManagement((sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                .authorizeHttpRequests((request -> request
                        .requestMatchers(HttpMethod.POST,"/api/login").permitAll()
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/doctor/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/patient/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("securityFilterChain");
//        return httpSecurity.csrf().disable().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST, "/api/login")
//                .permitAll()
//                .requestMatchers(HttpMethod.DELETE, "/api/doctor/{id}").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/api/patient/{id}").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
