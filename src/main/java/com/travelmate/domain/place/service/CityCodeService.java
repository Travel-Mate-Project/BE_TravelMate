package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.dto.CityCodeResponse;
import com.travelmate.domain.place.repository.CityCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityCodeService {

    private final CityCodeRepository cityCodeRepository;

    /**
     * 나라를 선택하면 해당 나라의 지역 반환
     * @param countryId
     * @return
     */
    public List<CityCodeResponse> getAllRegionsByCountryId(Integer countryId) {

        List<CityCode> cityCodes = cityCodeRepository.findCodesAndNamesByCountryId(countryId);

        return cityCodes.stream()
                .map(this::toCityCodeResponse)
                .collect(Collectors.toList());
    }

    private CityCodeResponse toCityCodeResponse(CityCode cityCode){
        return new CityCodeResponse(
              cityCode.getCode()
            , cityCode.getName()
        );
    }

}
