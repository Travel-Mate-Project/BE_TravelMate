package com.travelmate.commons.exception.dto;

import com.travelmate.commons.web.EmptyResponse;
import lombok.Getter;

@Getter
public class ClientErrorResponse {

    private final String code;
    private final String message;
    private final EmptyResponse data;

    public ClientErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        data = new EmptyResponse();
    }
}
