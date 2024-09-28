package com.travelmate.domain.course.dto.response;

import com.travelmate.domain.course.domain.Course;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CourseResponse(
        Integer id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String placeList,
        Integer totalTime,
        String saveYn,
        String delYn
) {
    public static CourseResponse of(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .placeList(course.getPlaceList())
                .totalTime(course.getTotalTime())
                .saveYn(course.getSaveYn())
                .delYn(course.getDelYn())
                .build();
    }
}
