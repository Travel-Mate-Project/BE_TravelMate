package com.travelmate.domain.place.service;

import com.travelmate.domain.auth.dto.ClientUserDetails;
import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.domain.Review;
import com.travelmate.domain.place.dto.ReviewRequest;
import com.travelmate.domain.place.dto.ReviewResponse;
import com.travelmate.domain.place.repository.PlaceRepository;
import com.travelmate.domain.place.repository.ReviewRepository;
import com.travelmate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;


    // 리뷰 생성
    public void createReview(ReviewRequest reviewRequest) {

        Place place = null;
        if(reviewRequest.getPlaceId() != null){
            place = placeRepository.findById(reviewRequest.getPlaceId())
                    .orElseThrow(() -> new IllegalArgumentException(""));
        }

        Review review = Review.builder()
                .place(place)
                .content(reviewRequest.getContent())
                .rating(reviewRequest.getRating())
                .build();

        reviewRepository.save(review);

        // Place 평균 평점 업데이트 로직
        updatePlaceAverageRating(place.getPlaceId());

    }

    // 리뷰 생성 시 해당 Place의 평균 평점 업데이트
    private void updatePlaceAverageRating(Long placeId){
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Id에 해당하는 장소가 존재하지 않습니다."));

        Double averageRating = reviewRepository.calculateAverageRatingByPlaceId(placeId);

        place.setAverageRating(averageRating);
        placeRepository.save(place);
    }


    // Place의 리뷰 가져오기
    public List<ReviewResponse> getReviewsByPlaceId(Long placeId) {
        return null;
    }

    public ReviewResponse toReviewResponse(Review review){

        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getRating(),
                review.getUser().getUserName(),
                review.getCreatedAt()
        );
    }
}
