package com.ges_abs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Autorise toutes les routes sous /api
                .allowedOrigins(
                        "https://angular-hackaton.vercel.app/",
                        "https://angular-hackaton-lkingtxq6-victorin-attolodes-projects.vercel.app/"
                ) // Autorise Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP permises
                .allowedHeaders("*") // Tous les en-têtes
                .allowCredentials(true); // Optionnel, si tu utilises des cookies
    }
}