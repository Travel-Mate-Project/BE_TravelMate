package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.code.CityCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
    List<Place> findPlacesByCityCodeId(CityCode cityCode);

    // 나라의 특정 지역 선택 시 해당 지역의 "인기 맛집"을 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    List<Place> findByCityCodeIdAndTypeOrderByLikeCountDesc(CityCode cityCode, String type, Pageable pageable);

    // 지역 "인기 명소"를 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    List<Place> findByCityCodeIdAndTypeNotOrderByLikeCountDesc(CityCode cityCode, String type, Pageable pageable);

    // 전체 "인기 명소"를 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    List<Place> findByTypeNotOrderByLikeCountDesc(String type, Pageable pageable);
}


