package com.travelmate.domain.user.controller;

import com.travelmate.commons.web.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("${server.api.prefix}/user")
public class UserController {
    @PostMapping("/test")
    public ApiResponse<String> test(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        System.out.println("???");
        return ApiResponse.OK("hi?");
    }
}
