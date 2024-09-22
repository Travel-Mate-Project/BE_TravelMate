package com.travelmate.domain.auth.controller.dto.request;

import com.travelmate.commons.exception.ValidationMessage;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = ValidationMessage.USER_ID_NOT_BLANK) String userEmail,
        @NotBlank(message = ValidationMessage.PASSWORD_NOT_BLANK) String password
) {
}
