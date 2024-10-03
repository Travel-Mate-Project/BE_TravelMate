package com.travelmate.domain.place.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponse {

    private Long reviewId;  // 리뷰ID
    private String content; // 리뷰 내용
    private int rating; // 평점
    private String userName; // 리뷰 작성자 이름
    private LocalDateTime createdAt; // 리뷰 작성 시간

}
