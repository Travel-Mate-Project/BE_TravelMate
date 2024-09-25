package com.travelmate.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceResponse {

    private Integer placeId; // 장소 ID
    private String name; // 이름
    private String description; // 설명
    private String addr; // 주소
    private String type; // 분류
    private Double latitude; // 위도
    private Double longitude; // 경도
    private Integer likeCount; // User에게 받은 좋아요 수

}
