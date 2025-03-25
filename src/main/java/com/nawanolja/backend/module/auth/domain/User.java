package com.nawanolja.backend.module.auth.domain;

import com.nawanolja.backend.core.exception.BadRequestException;
import com.nawanolja.backend.core.exception.ErrorCode;
import com.nawanolja.backend.module.auth.domain.vo.Gender;
import com.nawanolja.backend.module.auth.domain.vo.OAuth2ProviderType;
import com.nawanolja.backend.module.auth.domain.vo.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuth2ProviderType providerType;

    @Column(nullable = false)
    private LocalDate birth;

    private String refreshToken;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void validateSameOAuth2ProviderType(OAuth2ProviderType providerType) {
        if(!this.providerType.equals(providerType)){
            throw new BadRequestException(ErrorCode.DUPLICATE_USER);
        };
    }

    public void storeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
