package com.travelmate.domain.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    ACCESS("travelMateAccessToken", 24 * 60 * 60 * 1000L), // 24시간
    REFRESH("travelMateRefreshToken", 14 * 24 * 60 * 60 * 1000L), // 2주
    ;
    private final String key;
    private final long expiresIn;
}
