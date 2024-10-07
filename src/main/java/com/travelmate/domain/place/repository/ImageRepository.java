package com.travelmate.domain.place.repository;

import com.travelmate.domain.place.domain.Image;
import com.travelmate.domain.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // 특정 Place 객체를 통해 이미지 리스트 조회
    List<Image> findByPlace(Place place);
}
