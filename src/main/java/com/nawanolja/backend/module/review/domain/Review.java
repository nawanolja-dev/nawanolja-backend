package com.nawanolja.backend.module.review.domain;

import com.nawanolja.backend.module.review.domain.vo.Preference;
import com.nawanolja.backend.module.review.domain.vo.Content;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Content content;

    //이건 태그를 우리가 정해놓는지? 아니면 작성자들이 하는대로 하는지 ?그럼 defence 를 어떻게 할지

    private String tag;


    //네이버 ,카카오 맵 API 어떤 거 쓸지 ?
    private String locate;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferences")
    private Preference preference;


    //TODO
    //photo, video 추후 S3 인프라 어떤 거 쓸지 ? 알려조
    //photo 5개 video 1개

    private Long userID;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
    }
