package com.ges_abs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Origines autorisées
       config.setAllowedOrigins(List.of(
               "https://angular-hackaton.vercel.app",
               "https://angular-hackaton-lkingtxq6-victorin-attolodes-projects.vercel.app",
               "https://dev-back-end-sd0s.onrender.com",

       ));
        // config.setAllowedOriginPatterns(List.of("*"));

        // Méthodes HTTP autorisées
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        // Headers autorisés (dont Authorization pour JWT)
        config.setAllowedHeaders(List.of("*"));

        // Autoriser l'envoi de cookies / credentials (ex: token dans les headers)
        config.setAllowCredentials(true);

        // Enregistrement de la config pour toutes les routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}