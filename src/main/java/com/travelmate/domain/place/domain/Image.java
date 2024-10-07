package com.travelmate.domain.place.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId; // 이미지 ID(기본키)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = true)
    private Place place;

}
