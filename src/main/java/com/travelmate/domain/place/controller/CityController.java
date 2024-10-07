package com.travelmate.domain.place.controller;

import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.place.dto.response.CityCodeResponse;
import com.travelmate.domain.place.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${server.api.prefix}/city")
@RequiredArgsConstructor
@Slf4j
public class CityController {
    private final CityService cityService;

    @Operation(summary = "국가 코드에 해당하는 지역 반환", description = "국가 코드 전달받아 해당 국가의 모든 도시 목록 반환")
    @GetMapping
    public ApiResponse<List<CityCodeResponse>> getAllCitiesByCountryId(
            @Parameter(description = "조회할 국가의 ID", example = "1") @RequestParam("countryId") Integer countryId) {
            log.info("### Enter getAllCitiesByCountryId with countryId: {} ###", countryId);

        return ApiResponse.OK(cityService.getAllCitiesByCountryId(countryId));
    }
}
