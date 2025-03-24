package com.nawanolja.backend.module.review.domain.vo;


import com.nawanolja.backend.core.exception.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Content {

    @Column(name = "contents")
    private String value;

    public Content(String review) {
        validate(review);
        this.value = review;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException(("리뷰 내용은 필수 값입니다"));
        }
    }
}