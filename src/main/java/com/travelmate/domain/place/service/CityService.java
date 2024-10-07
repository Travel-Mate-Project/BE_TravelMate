package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.dto.response.CityCodeResponse;
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
    public List<CityCodeResponse> getAllCitiesByCountryId(Integer countryId) {

        List<CityCode> cityCodes = cityRepository.findCodesAndNamesByCountryId(countryId);

        return cityCodes.stream()
                .map(CityCodeResponse::of)
                .collect(Collectors.toList());
    }
}
