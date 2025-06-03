//package com.ges_abs.config;
//
//import com.ges_abs.security.JwtRequestFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtRequestFilter jwtRequestFilter;
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(JwtRequestFilter jwtRequestFilter, UserDetailsService userDetailsService) {
//        this.jwtRequestFilter = jwtRequestFilter;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable()) // csrf() est déprécié, on utilise csrf(lambda)
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/web/auth/login", "/api/mobile/auth/login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .and()
//                .build();
//    }
//}
