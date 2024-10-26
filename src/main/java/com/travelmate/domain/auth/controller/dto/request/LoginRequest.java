package com.travelmate.domain.auth.controller.dto.request;

import com.travelmate.commons.exception.ValidationMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @Schema(description = "사용자 이메일", example = "travelMate@naver.com", required = true)
        @NotBlank(message = ValidationMessage.USER_ID_NOT_BLANK)
        String userEmail,

        @Schema(description = "사용자 비밀번호", example = "password123", required = true)
        @NotBlank(message = ValidationMessage.PASSWORD_NOT_BLANK)
        String password
) {}