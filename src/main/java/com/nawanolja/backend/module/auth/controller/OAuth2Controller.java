package com.nawanolja.backend.module.auth.controller;

import com.nawanolja.backend.core.dto.ApiResponse;
import com.nawanolja.backend.module.auth.application.OAuth2Service;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2CallbackResponse;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2Response;
import com.nawanolja.backend.module.auth.domain.vo.LoginType;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/authorize")
    public ApiResponse<String> getOAuth2LoginUrl(@RequestParam("providerType") OAuth2ProviderType providerType) {
        String loginUrl = oAuth2Service.getOAuth2LoginUrl(providerType);

        return ApiResponse.ok(loginUrl);
    }

    @GetMapping("/login/{providerType}")
    public ApiResponse<OAuth2CallbackResponse> oAuthCallback(@PathVariable(value = "providerType") LoginType providerType, @RequestParam("code") String code, HttpServletResponse servletResponse) throws IOException {
        return ApiResponse.ok(new OAuth2CallbackResponse("ACCESSTOKEN_TEST"));
    }
}

