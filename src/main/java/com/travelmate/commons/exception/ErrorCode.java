package com.travelmate.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /** 400 BAD_REQUEST */
    USER_EMAIL_DUPLICATED_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000", "다른 이메일을 입력하세요. (중복 이메일)"),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "40000", "존재하지 않는 회원입니다."),
    INVALID_ID_PW(HttpStatus.BAD_REQUEST, "40000", "회원 id, pw가 잘못되었습니다."),
    INVALID_COURSE_DATE(HttpStatus.BAD_REQUEST,"40000", "코스 시작일과 종료일을 다시 확인해주세요."),

    /** 401 UNAUTHORIZED */
    REFRESH_TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "40100", "로그인 유효기간이 만료되었습니다. 다시 로그인해주세요."),
    NOT_VALID_TOKEN_UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED, "40100", "인증 정보와 로그인 정보가 일치하지 않습니다. 유효하지 로그인으로 간주되고 있습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
