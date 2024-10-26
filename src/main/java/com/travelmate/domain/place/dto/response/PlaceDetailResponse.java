package com.travelmate.domain.place.dto.response;

import com.travelmate.domain.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaceDetailResponse {

    private Long placeId; // 장소 ID
    private String name; // 장소 이름
    private String cityName; // 도시 이름 (e.g., 제주 제주시)
    private String description; // 장소 설명
    private String address; // 주소
    private String contact; // 연락처
    private String website; // 웹사이트
    private String operatingHours; // 운영시간
    private String parking; // 주차 정보
    private String holiday; // 휴일 정보
    private String rating; // 평점
    private Integer reviewCount; // 리뷰 개수
    private List<String> images; // 이미지 리스트 (firstImage + user-uploaded images)
    private List<ReviewResponse> reviews; // 후기 리스트

    public static PlaceDetailResponse of(Place place, String rating, int reviewCount, List<String> images, List<ReviewResponse> reviews) {
        return new PlaceDetailResponse(
                place.getPlaceId(),
                place.getName(),
                place.getCityId().getName(), // 도시 이름
                place.getDescription(),
                place.getAddr(),
                place.getContact(),
                place.getWebsite(),
                place.getOperatingHours(),
                place.getParking(),
                place.getHoliday(),
                rating,
                reviewCount,
                images,
                reviews
        );
    }
}
