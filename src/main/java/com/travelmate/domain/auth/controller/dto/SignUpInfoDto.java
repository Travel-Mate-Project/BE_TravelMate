package com.travelmate.domain.auth.controller.dto;

import com.travelmate.domain.user.domain.User;

public record SignUpInfoDto(User user) {

    public static SignUpInfoDto of(final User user) {
        return new SignUpInfoDto(user);
    }
}
