package com.travelmate.domain.place.controller;

import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.place.dto.request.ReviewRequest;
import com.travelmate.domain.place.dto.response.ReviewResponse;
import com.travelmate.domain.place.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${server.api.prefix}/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/place")
    public ApiResponse<List<ReviewResponse>> getPlaceReviews(@RequestParam Long placeId){
        List<ReviewResponse> reviews = reviewService.getReviewsByPlaceId(placeId);
        return null;
    }

    @PostMapping
    public ApiResponse<String> createReview(@RequestBody ReviewRequest reviewRequest){
        reviewService.createReview(reviewRequest);
        return ApiResponse.OK("");
    }

}
