package com.nawanolja.backend.module.auth.infra.security.authentication;

import com.nawanolja.backend.module.auth.domain.User;
import com.nawanolja.backend.module.auth.domain.vo.UserRole;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class NanolAuthentication extends UsernamePasswordAuthenticationToken {

    private User user;

    public NanolAuthentication(Object principal, Object credentials, User user) {
        super(principal, credentials, authorities(user.getRole()));
        this.user = user;
    }
    public NanolAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
        this.user = null;
    }

    private static Collection<? extends GrantedAuthority> authorities(UserRole role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
    }


    @Override
    public String getName(){
        return String.valueOf(user.getEmail());
    }
}
