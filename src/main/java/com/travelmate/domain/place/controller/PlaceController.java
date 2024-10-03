package com.travelmate.domain.place.controller;


import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.place.dto.response.PlaceDetailResponse;
import com.travelmate.domain.place.dto.response.PlaceResponse;
import com.travelmate.domain.place.service.PlaceService;
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
@RequestMapping("${server.api.prefix}/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "지역 선택 시 여행지 반환", description = "지역 코드 전달받아 해당 지역의 모든 여행지 반환")
    @GetMapping("/cityCode")
    public List<PlaceResponse> getPlacesByCityCode(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam("cityCodeId") Integer cityCodeId) {
        log.info("### getplaceByCityCode cityCode : " + cityCodeId + "###");
        return placeService.getPlacesByCityCode(cityCodeId);
    }

    @Operation(summary = "지역 선택 시 인기 여행지(명소) 반환", description = "지역 코드 전달받아 해당 지역의 인기 여행지를 좋아요 순으로 반환")
    @GetMapping("/popular/places")
    public List<PlaceResponse> getPopularPlaces(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam(value = "cityCodeId", required = false) Integer cityCodeId) {
        log.info("### getPopularPlaces cityCode : " + cityCodeId + "###");
        return placeService.getPopularPlaces(cityCodeId);
    }

    @Operation(summary = "지역 선택 시 인기 맛집 반환", description = "지역 코드 전달받아 해당 지역의 인기 맛집을 좋아요 순으로 반환")
    @GetMapping("/popular/restaurants")
    public List<PlaceResponse> getPopularRestaurants(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam("cityCodeId") Integer cityCodeId) {
        log.info("### getPopularRestaurants cityCode : " + cityCodeId + "###");
        return placeService.getPopularRestaurants(cityCodeId);
    }

    @Operation(summary = "Place의 상세 정보 반환")
    @GetMapping("/detail")
    public ApiResponse<PlaceDetailResponse> getPlaceDetail(
            @Parameter(description = "상세 조회할 Place의 Id", example = "1") @RequestParam("placeId") Long placeId){
        PlaceDetailResponse placeDetailResponse = placeService.getPlaceDetail(placeId);

        return ApiResponse.OK(placeDetailResponse);
    }



}
