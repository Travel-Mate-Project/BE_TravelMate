package com.travelmate.commons.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CookieName {
    ACCESS_TOKEN("accessToken", "Access Token"),
    REFRESH_TOKEN("refreshToken", "Refresh Token"),
    ;

    private final String code;
    private final String comment;

    public String getCode() {
        return this.code;
    }
}