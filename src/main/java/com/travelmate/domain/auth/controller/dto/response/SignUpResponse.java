package com.travelmate.domain.auth.controller.dto.response;

import java.util.Map;

public record SignUpResponse(long memberId, String accessToken, String refreshToken) {
    public static SignUpResponse of(final long memberId, Map<String, String> tokens) {
        return new SignUpResponse(memberId, tokens.get("accessToken"), tokens.get("refreshToken"));
    }
}
