package com.travelmate.domain.place.service;

import com.travelmate.domain.place.domain.Image;
import com.travelmate.domain.place.domain.Place;
import com.travelmate.domain.place.repository.ImageRepository;
import com.travelmate.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final PlaceRepository placeRepository;

    // placeId로 이미지를 조회하는 Method
    public List<String> getImageUrlsByPlaceId(Long placeId) {
        // 먼저 Place 객체를 가져옴
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Place ID: " + placeId));

        // Place 객체를 사용하여 이미지 리스트 조회
        List<Image> images = imageRepository.findByPlace(place);

        // 이미지 URL 리스트 반환
        return images.stream()
                .map(Image::getImageUrl) // 이미지 객체에서 URL만 추출
                .collect(Collectors.toList());
    }

}
