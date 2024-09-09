package com.travelmate.domain.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    ACCESS("shopAccessToken", 24 * 60 * 60 * 1000L),
    REFRESH("shopRefreshToken", 14 * 24 * 60 * 60 * 1000L),
    ;
    private final String key;
    private final long expiresIn;
}
