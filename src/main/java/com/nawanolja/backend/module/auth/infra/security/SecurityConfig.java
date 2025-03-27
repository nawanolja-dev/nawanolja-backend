package com.nawanolja.backend.module.auth.infra.security;

import com.nawanolja.backend.core.util.HttpServletUtils;
import com.nawanolja.backend.module.auth.infra.security.authentication.TokenAuthenticationProvider;
import com.nawanolja.backend.module.auth.infra.security.jwt.JwtFilter;
import com.nawanolja.backend.module.auth.infra.security.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenManager tokenManager;
    private final HttpServletUtils servletUtils;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/h2-console/**",
                "/resources/**",
                "/static/**"
            );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .anonymous(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(new JwtFilter(tokenManager,servletUtils, tokenAuthenticationProvider), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(request ->
                request.requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            )
            .build();
    }

}
