package com.travelmate.domain.place.controller;


import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.place.domain.PlaceType;
import com.travelmate.domain.place.dto.response.PlaceDetailResponse;
import com.travelmate.domain.place.dto.response.PlaceResponse;
import com.travelmate.domain.place.service.PlaceService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    @Hidden
    @Operation(summary = "지역 선택 시 여행지 반환", description = "지역 코드 전달받아 해당 지역의 모든 여행지 반환")
    @GetMapping("/cityCode")
    public ApiResponse<List<PlaceResponse>> getPlacesByCityCode(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam("cityCodeId") Integer cityCodeId) {

        return ApiResponse.OK(placeService.getPlacesByCityCode(cityCodeId));
    }

    @Hidden
    @Operation(summary = "지역 선택 시 인기 여행지(명소) 반환", description = "지역 코드 전달받아 해당 지역의 인기 여행지를 좋아요 순으로 반환")
    @GetMapping("/popular/places")
    public ApiResponse<List<PlaceResponse>> getPopularPlaces(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam(value = "cityCodeId", required = false) Integer cityCodeId) {

        return ApiResponse.OK(placeService.getPopularPlaces(cityCodeId));
    }

    @Hidden
    @Operation(summary = "지역 선택 시 인기 맛집 반환", description = "지역 코드 전달받아 해당 지역의 인기 맛집을 좋아요 순으로 반환")
    @GetMapping("/popular/restaurants")
    public ApiResponse<List<PlaceResponse>> getPopularRestaurants(
            @Parameter(description = "조회할 지역의 ID", example = "1") @RequestParam("cityCodeId") Integer cityCodeId) {
        log.info("### getPopularRestaurants cityCode : " + cityCodeId + "###");

        List<PlaceResponse> popularRestaurants = placeService.getPopularRestaurants(cityCodeId);

        return ApiResponse.OK(popularRestaurants);
    }

    @Operation(summary = "(Place 선택 시) Place의 상세 정보 반환")
    @GetMapping("/detail")
    public ApiResponse<PlaceDetailResponse> getPlaceDetail(
            @Parameter(description = "상세 조회할 Place의 Id", example = "1") @RequestParam("placeId") Long placeId){
        PlaceDetailResponse placeDetailResponse = placeService.getPlaceDetail(placeId);

        return ApiResponse.OK(placeDetailResponse);
    }

    // TODO: 여행지, 코스 해야함
    @Operation(summary = "(메인 페이지, 지역 선택 후) 가장 사랑 받는 여행지 리스트")
    @GetMapping("/popular")
    public ApiResponse<List<PlaceResponse>> getPopularPlace(
            @Parameter(description = "국가ID", example = "대한민국:1") @RequestParam int countryId,
            @Parameter(description = "CityID", example = "서울:1, 제주도:17") @RequestParam(required = false) Integer cityId,
            @Parameter(description = "여행지 카테고리", example = "ATTRACTION, RESTAURANT, CAFE") @RequestParam(required = false) PlaceType placeType) {

        List<PlaceResponse> popularPlace = placeService.getPopularPlaces(countryId, cityId, placeType);
        return ApiResponse.OK(popularPlace);
    }

    @Operation(summary = "(일정 생성 화면 > 장소 검색) 지역별 장소 리스트 반환")
    @GetMapping("/locations/list")
    public ApiResponse<List<PlaceResponse>> getPlaceByCity(
            @Parameter(description = "국가ID", example = "대한민국:1") @RequestParam int countryId,
            @Parameter(description = "CityID", example = "서울:1, 제주도:17") @RequestParam(required = true) Integer cityId,
            @Parameter(description = "여행지 카테고리", example = "ATTRACTION, RESTAURANT, CAFE") @RequestParam(required = false) PlaceType placeType) {

        List<PlaceResponse> placeList = placeService.getPlacesByCity(countryId, cityId, placeType);
        return ApiResponse.OK(placeList);
    }
}
