package com.nawanolja.backend.module.auth.infra.security.authentication;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.User;
import com.nawanolja.backend.module.auth.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider  {

    private final UserRepository userRepository;

    @Autowired
    public TokenAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Authentication getAuthentication(String memberId) {
        User user = getUser(memberId);

        return createAuthentication(user);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(User user) {
        return new NanolAuthentication(user.getEmail(), null, user);
    }

    private User getUser(String userId) {
        long id = Long.parseLong(userId);

        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_FOUND_USER));
    }
}