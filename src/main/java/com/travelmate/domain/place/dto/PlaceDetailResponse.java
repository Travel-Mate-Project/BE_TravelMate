package com.travelmate.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaceDetailResponse {

    private Long placeId; // 장소 ID
    private String name; // 장소 이름
    private String description; // 장소 설명
    private String address; // 주소
    private String contact; // 연락처
    private String website; // 웹사이트
    private String openHours; // 이용시간
    private String parking; // 주차 정보
    private String holiday; // 휴일 정보
    private String rating; // 평점
    private List<String> images; // 이미지 리스트
    private List<ReviewResponse> reviews; // 후기 리스트

}
