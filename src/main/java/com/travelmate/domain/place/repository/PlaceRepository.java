package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.Country;
import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.code.City;
import com.travelmate.domain.place.domain.PlaceType;
import com.travelmate.domain.place.dto.response.PlaceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
    //List<Place> findPlacesByCityCodeId(City city);

    // 나라의 특정 지역 선택 시 해당 지역의 "인기 맛집"을 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    //List<Place> findByCityCodeIdAndTypeOrderByLikeCountDesc(City city, String type, Pageable pageable);

    // 지역 "인기 명소"를 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    //List<Place> findByCityCodeIdAndTypeNotOrderByLikeCountDesc(City city, String type, Pageable pageable);

    // 전체 "인기 명소"를 좋아요 수로 내림차순 정렬, 상위 pageable 수만큼 가져오기(pageable이 20이면 20개)
    //List<Place> findByTypeNotOrderByLikeCountDesc(String type, Pageable pageable);

    @Query("SELECT p FROM Place p WHERE "
            + "(:country IS NULL OR p.countryId = :country) AND "
            + "(:city IS NULL OR p.cityId = :city) AND "
            + "p.type = :placeType "
            + "ORDER BY p.likeCount DESC")
    List<Place> findPopularPlacesByCountryCityAndType(@Param("country") Country country,
                                                      @Param("city") City city,
                                                      @Param("placeType") PlaceType placeType,
                                                      Pageable pageable);

    @Query("SELECT p FROM Place p WHERE "
            + "p.countryId = :country AND "
            + "p.type = :placeType "
            + "ORDER BY p.likeCount DESC")
    List<Place> findPopularPlacesByCountryAndType(
            @Param("country") Country country,
            @Param("placeType") PlaceType placeType,
            Pageable pageable);

    // Country, City, PlaceType 으로 장소 조회
    @Query("SELECT p FROM Place p WHERE p.countryId = :country AND p.cityId = :city AND p.type = :placeType ORDER BY p.likeCount DESC")
    List<Place> findPlacesByCountryCityAndType(@Param("country") Country country,
                                               @Param("city") City city,
                                               @Param("placeType") PlaceType placeType);

    // Country와 City로만 장소 조회
    @Query("SELECT p FROM Place p WHERE p.countryId = :country AND p.cityId = :city ORDER BY p.likeCount DESC")
    List<Place> findPlacesByCountryAndCity(@Param("country") Country country,
                                           @Param("city") City city);
}

