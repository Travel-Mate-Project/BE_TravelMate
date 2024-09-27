package com.travelmate.domain.auth.dto;

import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;

public record NewUserValidationDto(String userName, String userEmail) {

    public static NewUserValidationDto of(SignUpRequest request) {
        return new NewUserValidationDto(
                request.userName(),
                request.userEmail());
    }
}
