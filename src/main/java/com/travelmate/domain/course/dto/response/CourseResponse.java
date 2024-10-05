package com.travelmate.domain.course.dto.response;

import com.travelmate.domain.course.domain.Course;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CourseResponse(
        Integer id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String placeList,
        Integer totalTime,
        String displayYn,
        String saveYn,
        String delYn,
        LocalDateTime regiDtm,
        String regiId,
        LocalDateTime updtDtm,
        String updtId
) {
    public static CourseResponse of(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .placeList(course.getPlaceList())
                .totalTime(course.getTotalTime())
                .displayYn(course.getDisplayYn())
                .saveYn(course.getSaveYn())
                .delYn(course.getDelYn())
                .regiDtm(course.getRegiDtm())
                .regiId(course.getRegiId())
                .updtDtm(course.getUpdtDtm())
                .updtId(course.getUpdtId())
                .build();
    }
}
