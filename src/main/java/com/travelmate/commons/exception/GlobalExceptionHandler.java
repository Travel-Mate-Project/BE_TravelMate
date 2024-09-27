package com.travelmate.commons.exception;

import com.travelmate.commons.exception.dto.ClientErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientRuntimeException.class)
    public ResponseEntity<ClientErrorResponse> handleCustomMessageRuntimeException(
            final ClientRuntimeException e) {
        return makeErrorResponse(e.getCode(), e.getMessage(), e.getHttpStatus());
    }

    private ResponseEntity<ClientErrorResponse> makeErrorResponse(
            String code, String message, HttpStatus status) {
        ClientErrorResponse response = new ClientErrorResponse(code, message);
        return new ResponseEntity<>(response, status);
    }
}
