package com.travelmate.domain.place.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country_translation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String languageCode; // 언어 코드 (예: 'ko', 'en')

    @Column(length = 255, nullable = false)
    private String countryName; // 국가 이름

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
