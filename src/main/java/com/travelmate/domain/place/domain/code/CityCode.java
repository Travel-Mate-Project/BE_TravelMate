package com.travelmate.domain.place.domain.code;

import com.travelmate.domain.place.domain.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city_code")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityCodeId;

    private Integer code; // openAPI에서 사용하는 도시 코드

    @Column(nullable = false, length = 255)
    private String name; // 도시 이름

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country; // 외래 키로 국가 연결

}
