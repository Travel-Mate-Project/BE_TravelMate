package com.travelmate.domain.auth.controller;

import com.travelmate.commons.service.port.SystemHolder;
import com.travelmate.commons.web.ApiResponse;
import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.auth.controller.dto.SignUpInfoDto;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.auth.controller.dto.response.SignUpResponse;
import com.travelmate.domain.auth.domain.TokenType;
import com.travelmate.domain.auth.service.AuthFacadeService;
import com.travelmate.domain.auth.service.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/auth")
public class AuthController {

    private final AuthFacadeService authFacadeService;
    private final TokenProvider tokenProvider;
    private final SystemHolder systemHolder;

    @PostMapping("/signup") // 회원가입
    public ApiResponse<SignUpResponse> signUp(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @Valid @RequestBody SignUpRequest request) {
        System.out.println("signup post request : " + request);
        final SignUpInfoDto dto = authFacadeService.signUp(request);
        final Map<String, String> tokens =
                issueToken(httpServletResponse, dto.user());
        return ApiResponse.OK(SignUpResponse.of( dto.user().getUserId(), tokens));
    }

    private Map<String, String> issueToken(
            final HttpServletResponse httpServletResponse,
            final User user
            ) {
        String accessToken = tokenProvider.generateToken(TokenType.ACCESS, user, systemHolder);
        String refreshToken = tokenProvider.generateToken(TokenType.REFRESH, user, systemHolder);
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }
}
