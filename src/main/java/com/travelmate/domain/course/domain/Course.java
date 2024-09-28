package com.travelmate.domain.course.domain;

import com.travelmate.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 장소 ID (기본키)

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String placeList;
    private Integer totalTime;
    private String saveYn;
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
