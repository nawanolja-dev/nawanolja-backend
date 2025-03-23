package com.nawanolja.backend.module.auth.controller.converter;

import com.nawanolja.backend.module.auth.domain.vo.LoginType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginTypeConverter implements Converter<String, LoginType> {
    @Override
    public LoginType convert(String source) {
        return LoginType.fromString(source);
    }
}
