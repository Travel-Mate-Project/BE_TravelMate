package com.travelmate.domain.place.domain.code;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 광역시, 도 코드 테이블
 */
@Entity
@Table(name = "region_code")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionCodeId; // 시도 ID(기본키)

    private Integer code; // 시도코드

    private String name; // 시도명

    @ManyToOne
    @JoinColumn(name = "city_code_id", nullable = false)
    private CityCode cityCode;
}
