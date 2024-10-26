package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.code.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    // 나라를 선택하면 해당 나라의 지역 반환
    @Query("SELECT c FROM City c WHERE c.country.countryId = ?1")
    List<City> findCodesAndNamesByCountryId(Integer countryId);

}
