package com.travelmate.domain.auth.exception;

import com.travelmate.commons.exception.ClientRuntimeException;
import com.travelmate.commons.exception.ErrorCode;

public class InvalidIdPwException extends ClientRuntimeException {

    public InvalidIdPwException() {
        super(ErrorCode.INVALID_ID_PW);
    }
}
