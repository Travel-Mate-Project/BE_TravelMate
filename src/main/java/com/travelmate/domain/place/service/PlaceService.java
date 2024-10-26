package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.Country;
import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.PlaceType;
import com.travelmate.domain.place.domain.Review;
import com.travelmate.domain.place.domain.code.City;
import com.travelmate.domain.place.dto.response.PlaceDetailResponse;
import com.travelmate.domain.place.dto.response.PlaceResponse;
import com.travelmate.domain.place.dto.response.ReviewResponse;
import com.travelmate.domain.place.repository.CityRepository;
import com.travelmate.domain.place.repository.CountryRepository;
import com.travelmate.domain.place.repository.PlaceRepository;
import com.travelmate.domain.place.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final ReviewRepository reviewRepository;

    private final ReviewService reviewService;
    private final ImageService imageService;

    // 지역 선택 시 여행지 반환
    public List<PlaceResponse> getPlacesByCityCode(Integer cityId) {

        City city = new City(); // CityCode 객체 생성
        city.setCityId(cityId); // ID 값 설정

        /*
        List<Place> placesByCityCodeId = placeRepository.findPlacesByCityCodeId(city);

        // Place 엔티티를 PlaceResponse DTO로 변환하여 리스트로 반환
        return placesByCityCodeId.stream()
                .map(this::toPlaceResponse)  // 각 Place 객체를 PlaceResponse로 변환
                .collect(Collectors.toList());
         */

        return null;
    }


    // 지역 선택 시 여행지 좋아요 수로 내림차순 정렬
    public List<PlaceResponse> getPopularPlaces(Integer cityId) {

        Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목

        List<Place> places;

        // TODO : 매직넘버 39 상수로 빼기
        if(cityId == null) {
            //places = placeRepository.findByTypeNotOrderByLikeCountDesc("39", pagingFilter);
        }
        else{
            City city = new City();
            city.setCityId(cityId);
            //places = placeRepository.findByCityCodeIdAndTypeNotOrderByLikeCountDesc(city, "39", pagingFilter);
        }


        /*
        return places.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());
         */

        return null;
    }

    // 지역 선택 시 해당 지역의 맛집을 좋아요 수로 내림차순 정렬
    public List<PlaceResponse> getPopularRestaurants(Integer cityId) {
        City city = new City();
        city.setCityId(cityId);


        Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목
        // TODO : 매직넘버 39 상수로 빼기
        //List<Place> places = placeRepository.findByCityCodeIdAndTypeOrderByLikeCountDesc(city, "39", pagingFilter);

        /*
        return places.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());
         */

        return null;
    }

    private PlaceResponse toPlaceResponse(Place place) {
        String cityName = place.getCityId().getName();
        int reviewCount = reviewRepository.countReviewByPlaceId(place.getPlaceId());

        Double averageRating = place.getAverageRating();
        String formattedAverageRating = averageRating != null ? String.format("%.1f", averageRating) : "0.0";

        // PlaceResponse에 firstImage만 포함
        //return PlaceResponse.of(place, formattedAverageRating, reviewCount, List.of(place.getFirstImage()));
        return PlaceResponse.of(place, formattedAverageRating, reviewCount, cityName);
    }

    // PlaceDetailResponse 반환
    public PlaceDetailResponse getPlaceDetail(Long placeId) {

        // PlaceId로 Place 조회
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Place ID가 유효하지 않습니다."));

        // 해당 Place의 리뷰 정보 조회
        List<Review> reviews = reviewRepository.findReviewsByPlaceId(placeId);

        // 리뷰를 ReviewResponse로 변환
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(reviewService::toReviewResponse)
                .collect(Collectors.toList());

        // 평점 형식 변환
        String averageRating = place.getAverageRating() != null ? String.format("%.1f", place.getAverageRating()) : "0.0";
        int reviewCount = reviewResponses.size();

        // 이미지 리스트 생성 (firstImage + 사용자 업로드 이미지)
        List<String> imageUrls = imageService.getImageUrlsByPlaceId(placeId);
        imageUrls.add(0, place.getFirstImage()); // firstImage를 리스트의 첫 번째에 추가

        // cityName을 포함한 PlaceDetailResponse 반환
        return PlaceDetailResponse.of(place, averageRating, reviewCount, imageUrls, reviewResponses);
    }

    // 가장 사랑 받는 여행지 리스트 반환
    public List<PlaceResponse> getPopularPlaces(int countryId, Integer cityCodeId, PlaceType placeType) {

        Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목

        // Country와 City가 없을 때 예외 발생
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Country ID가 유효하지 않습니다."));

        if (placeType == null) {
            throw new IllegalArgumentException("placeType은 필수 항목입니다.");
        }

        List<Place> popularPlaces;

        if(cityCodeId == null){
            // 전체 지역에서 가장 사랑 받는 여행지 조회
            popularPlaces = placeRepository.findPopularPlacesByCountryAndType(country, placeType, pagingFilter);
        }
        else{
            // 인기 여행지 리스트 조회 - countryId, cityCodeId, placeType 모두가 있을 경우
            City city = cityRepository.findById(cityCodeId)
                    .orElseThrow(() -> new IllegalArgumentException("City ID가 유효하지 않습니다."));

            popularPlaces = placeRepository.findPopularPlacesByCountryCityAndType(country, city, placeType, pagingFilter);
        }

        return popularPlaces.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());
    }



    // 지역별 장소 리스트 반환
    public List<PlaceResponse> getPlacesByCity(int countryId, Integer cityCodeId, PlaceType placeType) {
        //Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목

        // Country와 City가 없을 때 예외 발생
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Country ID가 유효하지 않습니다."));
        City city = cityRepository.findById(cityCodeId)
                .orElseThrow(() -> new IllegalArgumentException("City ID가 유효하지 않습니다."));

        List<Place> places;

        if (placeType == null) {
            // placeType이 없을 경우, country와 city만으로 장소 조회
            places = placeRepository.findPlacesByCountryAndCity(country, city);
        } else {
            // placeType이 있을 경우, country, city, placeType으로 필터링하여 장소 조회
            places = placeRepository.findPlacesByCountryCityAndType(country, city, placeType);
        }

        return places.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());
    }
}


