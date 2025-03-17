package com.nawanolja.backend.module.auth.domain;

import com.nawanolja.backend.module.auth.domain.vo.Gender;
import com.nawanolja.backend.module.auth.domain.vo.Password;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    @Embedded
    @AttributeOverrides(
        @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false))
    )
    private Password password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
