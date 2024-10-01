package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.Image;
import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.Review;
import com.travelmate.domain.place.domain.code.CityCode;
import com.travelmate.domain.place.dto.PlaceDetailResponse;
import com.travelmate.domain.place.dto.PlaceResponse;
import com.travelmate.domain.place.dto.ReviewRequest;
import com.travelmate.domain.place.dto.ReviewResponse;
import com.travelmate.domain.place.repository.PlaceRepository;
import com.travelmate.domain.place.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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

    /**
     * 나라의 특정 지역 선택 시 해당 지역의 여행지 반환
     *
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

        // 해당 Place의 ImageUrl List 조회
        List<String> imageUrls = imageService.getImageUrlsByPlaceId(place.getPlaceId());

        int reviewCount = reviewRepository.countReviewByPlaceId(place.getPlaceId());

        Double averageRating = place.getAverageRating();
        String formattedAverageRating = averageRating != null ? String.format("%.1f", averageRating) : "0.0";

        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                place.getDescription(),
                place.getAddr(),
                place.getType(),
                place.getLatitude(),
                place.getLongitude(),
                place.getLikeCount(),
                formattedAverageRating,
                reviewCount,
                imageUrls
        );
    }

    /**
     * 나라의 특정 지역 선택 시 해당 지역의 여행지를 좋아요 수로 내림차순 정렬
     * pageable수만큼 가져옴
     * @param cityCodeId
     * @return
     */
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

    /**
     * 나라의 특정 지역 선택 시 해당 지역의 맛집을 좋아요 수로 내림차순 정렬
     * pageable수만큼 가져옴
     * @param cityCodeId
     * @return
     */
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


    // Place의 상세 정보 반환
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

        return new PlaceDetailResponse(
                place.getPlaceId(),
                place.getName(),
                place.getDescription(),
                place.getAddr(),
                place.getContact(),
                place.getWebsite(),
                place.getOpenHours(),
                place.getParking(),
                place.getHoliday(),
                place.getAverageRating().toString(),
                null,
                reviewResponses
        );
    }
}


