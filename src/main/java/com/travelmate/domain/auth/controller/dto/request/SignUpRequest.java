package com.travelmate.domain.auth.controller.dto.request;

import com.travelmate.commons.exception.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder // test only
public record SignUpRequest(
        @NotBlank(message = ValidationMessage.USER_ID_NOT_BLANK) String userEmail,
        @NotBlank(message = ValidationMessage.PASSWORD_NOT_BLANK) String password,
        @NotBlank(message = ValidationMessage.NAME_NOT_BLANK) String userName) {

}

