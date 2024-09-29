package com.travelmate.domain.course.dto.request;

import com.travelmate.domain.place.exception.CourseInvalidDateException;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CourseCreateRequest(
        Long user_id,
        @NotBlank(message = "코스 이름은 빈값일 수 없습니다.")
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String placeList,
        String displayYn,
        String saveYn
) {

    public void validate() {
        if (startDate.isAfter(endDate)) {
            throw new CourseInvalidDateException();
        }
    }
}
