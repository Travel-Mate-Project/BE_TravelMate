package com.travelmate.domain.place.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/**
 * 나라 정보가 들어갈 테이블
 * ex) 대한민국에 대한 설명
 */
@Entity
@Table(name = "country")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CountryTranslation> translations;
}



