package com.nawanolja.backend.module.local.domain;

import com.nawanolja.backend.module.local.domain.vo.LocalType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false, length = 2)
    private String sidoCode;

    @Column(nullable = false, length = 3)
    private String sggCode;

    @Column(length = 3)
    private String emdCode;

    @Column(length = 2)
    private String riCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocalType localType;

    @Column(length = 10)
    private String sido;

    @Column(length = 10)
    private String sgg;

    @Column(length = 10)
    private String emd;

    @Column(length = 10)
    private String ri;

    @Column(length = 50)
    private String fullName;
}
