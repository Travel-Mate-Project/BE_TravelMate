package com.travelmate.domain.auth.exception;

import com.travelmate.commons.exception.ClientRuntimeException;
import com.travelmate.commons.exception.ErrorCode;

public class UserNotFoundException  extends ClientRuntimeException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND_EXCEPTION);
    }
}
