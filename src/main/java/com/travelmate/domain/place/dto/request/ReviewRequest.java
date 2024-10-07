package com.travelmate.domain.place.dto.request;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ReviewRequest {

    private String content;     // 리뷰내용
    private int rating;         // 평가 별점
    private Long userId;        // 작성자 ID
    private Long courseId;      // 코스에 대한 리뷰
    private Long placeId;       // 장소에 대한 리뷰
    private Long parentReviewId;// 대댓글인경우 부모 리뷰 ID
}
