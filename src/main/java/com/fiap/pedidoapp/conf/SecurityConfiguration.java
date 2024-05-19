package com.fiap.pedidoapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomJwtAuthenticationConverter authenticationConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz.requestMatchers("/", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**")
                .permitAll()
                .requestMatchers("private/**").hasAnyRole("CLIENTE")
                .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 ->oauth2
                .jwt(jwt -> 
                            jwt.jwtAuthenticationConverter(authenticationConverter) // Necessary to convert AWS Cognito claim "cognito:groups" into ROLES
                )
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
