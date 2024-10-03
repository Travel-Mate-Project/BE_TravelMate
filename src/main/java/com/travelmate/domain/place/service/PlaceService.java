package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.Review;
import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.dto.response.PlaceDetailResponse;
import com.travelmate.domain.place.dto.response.PlaceResponse;
import com.travelmate.domain.place.dto.response.ReviewResponse;
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

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    private final ImageService imageService;

    // 지역 선택 시 여행지 반환
    public List<PlaceResponse> getPlacesByCityCode(Integer cityCodeId) {

        CityCode cityCode = new CityCode(); // CityCode 객체 생성
        cityCode.setCityCodeId(cityCodeId); // ID 값 설정

        List<Place> placesByCityCodeId = placeRepository.findPlacesByCityCodeId(cityCode);

        // Place 엔티티를 PlaceResponse DTO로 변환하여 리스트로 반환
        return placesByCityCodeId.stream()
                .map(this::toPlaceResponse)  // 각 Place 객체를 PlaceResponse로 변환
                .collect(Collectors.toList());
    }


    // 지역 선택 시 여행지 좋아요 수로 내림차순 정렬
    public List<PlaceResponse> getPopularPlaces(Integer cityCodeId) {

        Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목

        List<Place> places;

        // TODO : 매직넘버 39 상수로 빼기
        if(cityCodeId == null) {
            places = placeRepository.findByTypeNotOrderByLikeCountDesc("39", pagingFilter);
        }
        else{
            CityCode cityCode = new CityCode();
            cityCode.setCityCodeId(cityCodeId);
            places = placeRepository.findByCityCodeIdAndTypeNotOrderByLikeCountDesc(cityCode, "39", pagingFilter);
        }

        return places.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());

    }

    // 지역 선택 시 해당 지역의 맛집을 좋아요 수로 내림차순 정렬
    public List<PlaceResponse> getPopularRestaurants(Integer cityCodeId) {
        CityCode cityCode = new CityCode();
        cityCode.setCityCodeId(cityCodeId);

        Pageable pagingFilter = PageRequest.of(0, 20); // 첫 페이지에 20개 항목
        // TODO : 매직넘버 39 상수로 빼기
        List<Place> places = placeRepository.findByCityCodeIdAndTypeOrderByLikeCountDesc(cityCode, "39", pagingFilter);

        return places.stream()
                .map(this::toPlaceResponse)
                .collect(Collectors.toList());
    }

    // PlaceResponse 반환
    private PlaceResponse toPlaceResponse(Place place) {

        // 해당 Place의 ImageUrl List 조회
        List<String> imageUrls = imageService.getImageUrlsByPlaceId(place.getPlaceId());
        int reviewCount = reviewRepository.countReviewByPlaceId(place.getPlaceId());

        Double averageRating = place.getAverageRating();
        String formattedAverageRating = averageRating != null ? String.format("%.1f", averageRating) : "0.0";

        return PlaceResponse.of(place, formattedAverageRating, reviewCount, imageUrls);
    }

    // PlaceDetailResponse 반환
    public PlaceDetailResponse getPlaceDetail(Long placeId) {

        // PlaceId로 Place 조회
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException(""));

        // 해당 Place의 리뷰 정보 조회
        List<Review> reviews = reviewRepository.findReviewsByPlaceId(placeId);

        // 리뷰를 ReviewResponse로 변환
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(reviewService::toReviewResponse)
                .collect(Collectors.toList());

        List<String> imageUrls = imageService.getImageUrlsByPlaceId(placeId);

        // 평점 형식 변환
        String averageRating = place.getAverageRating() != null ? String.format("%.1f", place.getAverageRating()) : "0.0";

        return PlaceDetailResponse.of(place, averageRating, imageUrls, reviewResponses);
    }
}


