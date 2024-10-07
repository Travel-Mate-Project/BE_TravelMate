package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // 특정 Place의 리뷰 평균 평점 계산
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.place.placeId = :placeId")
    Double calculateAverageRatingByPlaceId(@Param("placeId") Long placeId);

    // 장소의 리뷰 개수 계산
    @Query("SELECT COUNT(r) FROM Review r WHERE r.place.placeId = :placeId")
    Integer countReviewByPlaceId(@Param("placeId") Long placeId);


    @Query("SELECT r FROM Review r WHERE r.place.placeId = :placeId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByPlaceId(@Param("placeId") Long placeId);

}
