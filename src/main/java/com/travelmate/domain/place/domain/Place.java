package com.travelmate.domain.place.domain;

import com.travelmate.domain.place.domain.code.City;
import com.travelmate.domain.place.domain.code.Region;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId; // 장소 ID

    @Column(nullable = false, length = 255)
    private String name; // 이름

    @Column(length = 3000)
    private String description; // 설명

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country countryId; // 외래 키로 국가 연결

    @ManyToOne
    @JoinColumn(name = "city_code_id", nullable = false)
    private City cityId; // 외래 키로 도시 연결

    @ManyToOne
    @JoinColumn(name = "region_code_id", nullable = false)
    private Region regionId; // 외래 키로 시도코드 연결

    @Column(length = 255)
    private String addr; // 주소

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaceType type;

    private Double latitude; // 위도

    private Double longitude; // 경도

    private Integer likeCount; // User에게 받은 좋아요 수

    private Double averageRating; // 평균 평점

    private String contact; // 연락처

    private String website; // 웹사이트

    private String operatingHours; // 영업시간

    private String parking; // 주차 정보

    private String holiday; // 휴일 정보

    private String infocenter; // 안내 전화번호

    private String firstMenu; // 대표 메뉴

    private String treatMenu; // 메뉴 항목

    private String smoking; // 흡연 여부

    private String packing; // 포장 가능 여부

    private String reservation; // 예약 가능 여부

    private String firstImage; // 대표 이미지

}
