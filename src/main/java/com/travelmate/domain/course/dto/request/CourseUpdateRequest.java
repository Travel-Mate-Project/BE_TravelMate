package com.travelmate.domain.course.dto.request;

import com.travelmate.domain.place.exception.CourseInvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CourseUpdateRequest(
        Integer id,
        Long userId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String placeList,
        String displayYn,
        String saveYn,
        String delYn,
        LocalDateTime regiDtm,
        String regiId
) {
        public void validate() {
                if(startDate == null || endDate == null) return;
                if(startDate.isAfter(endDate)) {
                        throw new CourseInvalidDateException();
                }
        }
}
