package com.travelmate.domain.place.domain;

import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.domain.code.RegionCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId; // 장소 ID (기본키)


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
}
