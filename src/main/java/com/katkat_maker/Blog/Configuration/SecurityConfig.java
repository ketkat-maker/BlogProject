package com.katkat_maker.Blog.Configuration;

import com.katkat_maker.Blog.Domain.entities.User;
import com.katkat_maker.Blog.Repositories.UserRepository;
import com.katkat_maker.Blog.Security.BlogUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html").permitAll()
                                .requestMatchers(POST, "/api/v1/auth/login").permitAll()
                                .requestMatchers(GET, "/api/v1/posts/drafts").authenticated()
                                .requestMatchers(GET, "/api/v1/categories/**").permitAll()
                                .requestMatchers(GET, "/api/v1/tags/**").permitAll()
                                .requestMatchers(GET, "/api/v1/posts/**").permitAll()
                                .requestMatchers(POST, "/api/v1/tags").permitAll()
                                .requestMatchers(DELETE, "/api/v1/tags/**").authenticated()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder coder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}