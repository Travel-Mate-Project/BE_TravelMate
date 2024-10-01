package com.travelmate.domain.place.controller;


import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.place.dto.PlaceDetailResponse;
import com.travelmate.domain.place.dto.PlaceResponse;
import com.travelmate.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    /**
     * 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
     * @param cityCodeId
     * @return
     */
    @GetMapping("/cityCode")
    public List<PlaceResponse> getPlacesByCityCode(@RequestParam("cityCodeId") Integer cityCodeId) {
        log.info("### getplaceByCityCode cityCode : " + cityCodeId + "###");
        return placeService.getPlacesByCityCode(cityCodeId);
    }

    /**
     * 나라의 특정 지역 선택 시 해당지역의 인기 여행지(명소) 반환
     * @param cityCodeId
     * @return
     */
    @GetMapping("/popular/places")
    public List<PlaceResponse> getPopularPlaces(@RequestParam(value = "cityCodeId", required = false) Integer cityCodeId) {
        log.info("### getPopularPlaces cityCode : " + cityCodeId + "###");
        return placeService.getPopularPlaces(cityCodeId);
    }

    @GetMapping("/popular/restaurants")
    public List<PlaceResponse> getPopularRestaurants(@RequestParam("cityCodeId") Integer cityCodeId) {
        log.info("### getPopularRestaurants cityCode : " + cityCodeId + "###");
        return placeService.getPopularRestaurants(cityCodeId);
    }

    @GetMapping("/detail")
    public ApiResponse<PlaceDetailResponse> getPlaceDetail(@RequestParam("placeId") Long placeId){
        PlaceDetailResponse placeDetailResponse = placeService.getPlaceDetail(placeId);

        return ApiResponse.OK(placeDetailResponse);
    }



}
