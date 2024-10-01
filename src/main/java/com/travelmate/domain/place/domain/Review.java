package com.travelmate.domain.place.domain;

import com.travelmate.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 리뷰내용

    private int rating; // 평점

    /*
    TODO : Course Merge 시 추가
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;
     */

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = true)
    private Place place;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; // 리뷰 작성자

    @ManyToOne
    @JoinColumn(name = "parent_review_id", nullable = true)
    private Review parentReview;

    @OneToMany(mappedBy = "parentReview", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("createdAt ASC")
    private List<Review> replies = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt; // 리뷰 작성 시간

    // 엔티티가 저장되기 전에 자동으로 현재 시간 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
