package com.travelmate.domain.auth.controller;

import com.travelmate.commons.service.port.SystemHolder;
import com.travelmate.commons.web.ApiResponse;
import com.travelmate.commons.web.EmptyResponse;
import com.travelmate.domain.auth.controller.dto.request.LoginRequest;
import com.travelmate.domain.auth.controller.dto.request.PasswordUpdateRequest;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.auth.controller.dto.request.WithdrawalRequest;
import com.travelmate.domain.auth.controller.dto.response.LoginResponse;
import com.travelmate.domain.auth.controller.dto.response.SignUpResponse;
import com.travelmate.domain.auth.domain.TokenType;
import com.travelmate.domain.auth.service.AuthFacadeService;
import com.travelmate.domain.auth.service.TokenProvider;
import com.travelmate.domain.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("${server.api.prefix}/auth")
public class AuthController {

    private final AuthFacadeService authFacadeService;
    private final TokenProvider tokenProvider;
    private final SystemHolder systemHolder;

    @PostMapping("/signup") // 회원가입
    public ApiResponse<SignUpResponse> signUp(
            @Valid @RequestBody SignUpRequest request) {
        final User user = authFacadeService.signUp(request);

        return ApiResponse.OK(SignUpResponse.of(user.getUserId()));
    }

    private Map<String, String> issueToken(
            final User user
            ) {
        String accessToken = tokenProvider.generateToken(TokenType.ACCESS, user, systemHolder);
        String refreshToken = tokenProvider.generateToken(TokenType.REFRESH, user, systemHolder);
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    @PostMapping("/login") // 로그인
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        // id, pw 맞는지 확인
        User user = authFacadeService.login(request);
        final Map<String, String> tokens =
                issueToken(user);
        return ApiResponse.OK(LoginResponse.of(user.getUserId(), tokens));
    }

    @PutMapping("/password") // 비밀번호 변경 TODO: 보류
    public ApiResponse<EmptyResponse> changePassword(
            @Valid @RequestBody PasswordUpdateRequest request
    ) {
        authFacadeService.updatePassword(request);
        return ApiResponse.NO_CONTENT();
    }

    @PutMapping("/withdrawal") // 회원탈퇴
    public ApiResponse<EmptyResponse> withdrawal(
            @Valid @RequestBody WithdrawalRequest request
    ) {
        authFacadeService.withdrawal(request);
        return ApiResponse.NO_CONTENT();
    }
}
