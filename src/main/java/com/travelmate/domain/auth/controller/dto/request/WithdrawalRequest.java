package com.travelmate.domain.auth.controller.dto.request;

import com.travelmate.commons.exception.ValidationMessage;
import jakarta.validation.constraints.Positive;

public record WithdrawalRequest(
        @Positive(message = ValidationMessage.USER_ID_NOT_BLANK) Long userId
) {
}
