package com.tpo.shopandpack.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity 
@RequiredArgsConstructor
public class SecutiryConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/shop/albums/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                
                        // Permitir acceso completo a la API
                        .requestMatchers("/api/**").permitAll()
                        
                        // Permitir acceso a endpoints de actuator (si los tienes)
                        .requestMatchers("/actuator/**").permitAll()
                        
                        // Permitir acceso a recursos estáticos
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        
                        // Cualquier otra petición también permitida
                        .anyRequest().permitAll()
                    )
                    
                    // Configurar headers para H2 Console
                    .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // Permitir iframes para H2 Console
                        .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())
                    )
                    
                    // Configurar sesión como stateless para APIs REST
                    .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir todos los orígenes
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // Permitir todos los métodos HTTP
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // Permitir todos los headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir credenciales
        configuration.setAllowCredentials(true);
        
        // Exponer headers de respuesta
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
