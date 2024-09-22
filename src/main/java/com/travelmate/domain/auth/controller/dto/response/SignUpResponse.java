package com.travelmate.domain.auth.controller.dto.response;

public record SignUpResponse(long memberId) {
    public static SignUpResponse of(final long memberId) {
        return new SignUpResponse(memberId);
    }
}
