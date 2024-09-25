package com.travelmate.domain.place.controller;

import com.travelmate.domain.place.dto.CityCodeResponse;
import com.travelmate.domain.place.service.CityCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cityCode")
@RequiredArgsConstructor
@Slf4j
public class CityCodeController {
    private final CityCodeService cityCodeService;


    /**
     * 나라를 선택하면 해당 나라의 지역 반환
     * @param countryId
     * @return
     */
    @GetMapping("")
    public List<CityCodeResponse> getAllRegionsByCountryId(@RequestParam("countryId") Integer countryId) {
        log.info("### Enter getAllRegionsByCountryId with countryId: {} ###", countryId);

        return cityCodeService.getAllRegionsByCountryId(countryId);
    }
}
