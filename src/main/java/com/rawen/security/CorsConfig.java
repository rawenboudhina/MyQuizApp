package com.rawen.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indique que cette classe contient des configurations Spring
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permettre tous les chemins
                .allowedOrigins("*") // Accepter toutes les origines
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permettre certains types de requêtes
                .allowedHeaders("*") // Permettre tous les headers
                .allowCredentials(false); // Ajuster selon vos besoins pour les cookies ou l'authentification
    }
}
