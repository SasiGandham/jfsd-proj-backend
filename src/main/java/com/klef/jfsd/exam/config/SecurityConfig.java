package com.klef.jfsd.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    // CORS configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Set the allowed frontend origin URL
        configuration.setAllowedOrigins(Arrays.asList("https://jfsd-proj-frontend-cc2c.vercel.app")); // Frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // If cookies or credentials are required

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply the config to all URLs
        return source;
    }

    // Security filter chain setup
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource()) // Apply CORS configuration
            .and()
            .csrf().disable() // Disable CSRF protection (for APIs)
            .authorizeHttpRequests(auth -> auth
                // Public URLs (do not require authentication)
                .requestMatchers(
                		"/api/users",
                    "/api/applications/create",        // Endpoint for creating applications
                    "/api/applications/user/{email}",
                    "/api/users/signup",               // Signup page
                    "/api/posts/all",                  // All posts
                    "/api/posts/add",                  // Add new posts
                    "/api/applications/politician/{email}",
                    "/api/users/{email}",              // Get user details by email
                    "/api/users/login",                // Login page
                    "/api/auth/**",                    // Auth-related paths
                    "/api/public/**",                  // New public path (Wildcard for all public APIs)
                    "/api/landing-page",               // Landing page
                    "/api/contact-us",                 // Contact us page
                    "/api/faq/**",                     // FAQ section
                    "/api/news/latest"                 // Latest news page
                ).permitAll() // Allow access to these routes without authentication
                .anyRequest().authenticated() // Secure all other routes (authentication required)
            );

        return http.build();
    }
}
