package com.nawanolja.backend.module.auth.controller.converter;

import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ProviderTypeConverter implements Converter<String, OAuth2ProviderType> {
    @Override
    public OAuth2ProviderType convert(String source) {
        return OAuth2ProviderType.fromString(source);
    }
}
