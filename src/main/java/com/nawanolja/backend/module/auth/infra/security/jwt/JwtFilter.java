package com.nawanolja.backend.module.auth.infra.security.jwt;

import com.nawanolja.backend.core.util.HttpServletUtils;
import com.nawanolja.backend.module.auth.infra.security.authentication.TokenAuthenticationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final HttpServletUtils servletUtils;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    public JwtFilter(TokenManager tokenManager, HttpServletUtils servletUtils, TokenAuthenticationProvider tokenAuthenticationProvider) {
        this.tokenManager = tokenManager;
        this.servletUtils = servletUtils;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request);

        // 액세스 토큰 존재
        if ( accessToken != null ) {
            //  유효한 액세스 토큰
            if ( tokenManager.validateToken(accessToken) ) {
                setAuthentication(accessToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = tokenAuthenticationProvider.getAuthentication(tokenManager.getSubject(accessToken));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        return servletUtils.getAccessToken(request).orElse(null);
    }

}
