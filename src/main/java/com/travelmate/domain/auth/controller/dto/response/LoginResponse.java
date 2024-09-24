package com.travelmate.domain.auth.controller.dto.response;

import java.util.Map;

public record LoginResponse(Long memberId, String accessToken, String refreshToken) {
    public static LoginResponse of(final long memberId, Map<String, String> tokens) {
        return new LoginResponse(memberId, tokens.get("accessToken"), tokens.get("refreshToken"));
    }
}
