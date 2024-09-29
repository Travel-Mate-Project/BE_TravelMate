package com.travelmate.domain.course.domain;

import com.travelmate.domain.course.dto.request.CourseCreateRequest;
import com.travelmate.domain.course.dto.request.CourseUpdateRequest;
import com.travelmate.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 장소 ID (기본키)

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String placeList;
    private Integer totalTime;
    private String displayYn;
    private String saveYn;
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime regiDtm;
    private String regiId;
    private LocalDateTime updtDtm;
    private String updtId;

    public static Course of(CourseCreateRequest request, User user) {
        return Course.builder()
                .user(user)
                .name(request.name())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .placeList(request.placeList())
                .displayYn(request.displayYn())
                .saveYn(request.saveYn())
                .delYn("N")
                .regiDtm(LocalDateTime.now())
                .regiId(user.getUserName())
                .build();
    }

    public static Course of(CourseUpdateRequest request, User user) {
        return Course.builder()
                .id(request.id())
                .user(user)
                .name(request.name())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .placeList(request.placeList())
                .displayYn(request.displayYn())
                .saveYn(request.saveYn())
                .delYn(request.delYn())
                .regiDtm(request.regiDtm())
                .regiId(request.regiId())
                .updtDtm(LocalDateTime.now())
                .updtId(user.getUserName())
                .build();
    }
}
