package com.nawanolja.backend.module.auth.controller;

import com.nawanolja.backend.core.dto.ApiResponse;
import com.nawanolja.backend.module.auth.application.OAuth2Service;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2LoginSuccess;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2Response;
import com.nawanolja.backend.module.auth.controller.dto.OAuth2SignupRequired;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<OAuth2Response> oAuthCallback(@PathVariable(value = "providerType") OAuth2ProviderType providerType, @RequestParam("code") String code)  {

        OAuth2Response response = oAuth2Service.handleOAuthLogin(providerType, code);

        if(response instanceof OAuth2SignupRequired) {
            OAuth2SignupRequired signupRequired = (OAuth2SignupRequired) response;
            return ApiResponse.created(signupRequired);
        } else if (response instanceof OAuth2LoginSuccess) {
            OAuth2LoginSuccess loginSuccess = (OAuth2LoginSuccess) response;
            return ApiResponse.ok(loginSuccess);
        }

        return null;
    }
}

