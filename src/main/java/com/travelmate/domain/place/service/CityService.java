package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.code.City;
import com.travelmate.domain.place.dto.response.CityResponse;
import com.travelmate.domain.place.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    // 국가 선택 시 해당 국가의 지역 반환(서울, 대구, 부산, 경기도, ....
    public List<CityResponse> getAllCitiesByCountryId(Integer countryId) {

        List<City> cities = cityRepository.findCodesAndNamesByCountryId(countryId);

        return cities.stream()
                .map(CityResponse::of)
                .collect(Collectors.toList());
    }
}
