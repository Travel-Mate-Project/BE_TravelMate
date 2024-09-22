package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.code.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;
import com.travelmate.domain.place.domain.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    // 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
    List<Place> findPlacesByCityCodeId(CityCode cityCode);

}

