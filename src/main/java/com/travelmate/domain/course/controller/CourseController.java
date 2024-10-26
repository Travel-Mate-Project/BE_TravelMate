package com.travelmate.domain.course.controller;

import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.course.domain.Course;
import com.travelmate.domain.course.dto.request.CourseCreateRequest;
import com.travelmate.domain.course.dto.request.CourseUpdateRequest;
import com.travelmate.domain.course.dto.response.CourseResponse;
import com.travelmate.domain.course.service.CourseService;
import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${server.api.prefix}/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @Hidden
    @GetMapping
    public ApiResponse<List<CourseResponse>> findAll() { //TODO: filter 추가하기
        List<Course> course = courseService.findAll();
        List<CourseResponse> courseResponses = course.stream()
                .map(CourseResponse::of)
                .collect(Collectors.toList());
        return ApiResponse.OK(courseResponses);
    }

    @Hidden
    @GetMapping("/{id}")
    public ApiResponse<CourseResponse> findById(@PathVariable("id") Integer id) {
        Course course = courseService.findBy(id);
        return ApiResponse.OK(CourseResponse.of(course));
    }

    @Hidden
    @PostMapping
    public ApiResponse<CourseResponse> save(@RequestBody @Valid CourseCreateRequest request) {
        request.validate();
        User user = userService.findByUserId(request.user_id());
        Course course = courseService.save(Course.of(request, user));
        return ApiResponse.OK(CourseResponse.of(course));
    }

    @Hidden
    @PutMapping
    public ApiResponse<CourseResponse> update(@RequestBody CourseUpdateRequest request) {
        request.validate();
        User user = userService.findByUserId(request.userId());
        Course course = courseService.updateById(Course.of(request, user));
        return ApiResponse.OK(CourseResponse.of(course));
    }
}
