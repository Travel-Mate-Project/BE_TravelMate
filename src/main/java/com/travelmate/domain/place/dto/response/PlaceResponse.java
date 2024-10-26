package com.travelmate.domain.place.dto.response;

import com.travelmate.domain.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaceResponse {

    private Long placeId; // 장소 ID
    private String name; // 이름
    private String description; // 설명
    private String addr; // 주소
    private String type; // 분류
    private Double latitude; // 위도
    private Double longitude; // 경도
    private Integer likeCount; // User에게 받은 좋아요 수
    private String averageRating; // 평균 평점
    private Integer reviewCount; // 댓글 개수
    private String firstImage; // 첫 번째 이미지 URL
    private String cityName; // 도시 이름

    public static PlaceResponse of(Place place, String averageRating, int reviewCount, String cityName){
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                place.getDescription(),
                place.getAddr(),
                place.getType().name(),
                place.getLatitude(),
                place.getLongitude(),
                place.getLikeCount(),
                averageRating,
                reviewCount,
                place.getFirstImage(), // firstImage 필드 추가
                cityName
        );
    }
}