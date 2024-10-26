package com.travelmate.domain.auth.controller.dto.request;

import com.travelmate.commons.exception.ValidationMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder // test only
public record SignUpRequest(
        @Schema(description = "사용자 이메일", example = "travelMate@naver.com", required = true)
        @NotBlank(message = ValidationMessage.USER_ID_NOT_BLANK) String userEmail,

        @Schema(description = "비밀번호", example = "password123", required = true)
        @NotBlank(message = ValidationMessage.PASSWORD_NOT_BLANK) String password,

        @Schema(description = "사용자 이름", example = "홍길동", required = true)
        @NotBlank(message = ValidationMessage.NAME_NOT_BLANK) String userName) {
}