package com.travelmate.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /** 400 BAD_REQUEST */
    USER_EMAIL_DUPLICATED_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000", "다른 이메일을 입력하세요. (중복 이메일)"),

    /** 401 UNAUTHORIZED */
    REFRESH_TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "40100", "로그인 유효기간이 만료되었습니다. 다시 로그인해주세요.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
