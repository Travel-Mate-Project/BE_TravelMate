package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.dto.PlaceResponse;
import com.travelmate.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    /**
     * 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
     * @param cityCodeId
     * @return
     */
    public List<PlaceResponse> getPlacesByCityCode(Integer cityCodeId) {

        CityCode cityCode = new CityCode(); // CityCode 객체 생성
        cityCode.setCityCodeId(cityCodeId); // ID 값 설정

        List<Place> placesByCityCodeId = placeRepository.findPlacesByCityCodeId(cityCode);

        // Place 엔티티를 PlaceResponse DTO로 변환하여 리스트로 반환
        return placesByCityCodeId.stream()
                .map(this::toPlaceResponse)  // 각 Place 객체를 PlaceResponse로 변환
                .collect(Collectors.toList());
    }

    private PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                place.getDescription(),
                place.getAddr(),
                place.getType(),
                place.getLatitude(),
                place.getLongitude(),
                place.getLikeCount()
        );
    }
}


