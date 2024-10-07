package com.travelmate.domain.place.domain;

import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.domain.code.RegionCode;
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
    private Long placeId; // 장소 ID (기
    @Column(nullable = false, length = 255)
    private String name; // 이름

    @Column(length = 3000)
    private String description; // 설명

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country countryId; // 외래 키로 국가 연결

    @ManyToOne
    @JoinColumn(name = "city_code_id", nullable = false)
    private CityCode cityCodeId; // 외래 키로 도시 연결

    @ManyToOne
    @JoinColumn(name = "region_code_id", nullable = false)
    private RegionCode regionCodeId; // 외래 키로 시도코드 연결

    @Column(length = 255)
    private String addr; // 주소

    @Column(length = 50)
    private String type; // 분류 (명소, 식당 등)

    private Double latitude; // 위도

    private Double longitude; // 경도

    private Integer likeCount; // User에게 받은 좋아요 수

    private Double averageRating; // 평균 평점

    private String contact; // 연락처

    private String website; // 웹사이트

    private String openHours; // 이용시간

    private String parking; // 주차 정보

    private String holiday; // 휴일 정보
}
