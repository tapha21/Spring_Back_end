package com.ges_abs.security;

import com.ges_abs.config.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http
                // Active CORS avec ta config CorsConfig
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                // Désactive CSRF pour REST API
                .csrf(AbstractHttpConfigurer::disable)

                // Configuration des règles d’accès
                .authorizeHttpRequests(auth -> auth
                        // Autorise Swagger UI et doc sans auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        
                        // Autorise login sans auth
                        .requestMatchers("/api/web/auth/login").permitAll()
                        .requestMatchers("/api/mobile/auth/login").permitAll()
                        
                        // Autorise upload image sans auth
                        .requestMatchers("/api/images/upload").permitAll()
                        
                        // Autorise toutes les requêtes OPTIONS (préflight CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        
                        // Exemple : les routes web sont pour ADMIN uniquement (à adapter)
                        .requestMatchers("/api/web/**").hasRole("ADMIN")
                        
                        // Autorise toutes les autres requêtes (à adapter selon ton besoin)
                        .anyRequest().authenticated()
                )

                // Pas de session (stateless JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Ajout du filtre JWT avant UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
