package com.travelmate.domain.course.controller;

import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.course.domain.Course;
import com.travelmate.domain.course.dto.response.CourseResponse;
import com.travelmate.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${server.api.prefix}/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ApiResponse<List<CourseResponse>> findAll() {
        List<Course> course = courseService.findAll();
        List<CourseResponse> courseResponses = course.stream()
                .map(CourseResponse::of)
                .collect(Collectors.toList());
        return ApiResponse.OK(courseResponses);
    }
}
