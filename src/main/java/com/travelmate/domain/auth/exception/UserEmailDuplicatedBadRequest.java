package com.travelmate.domain.auth.exception;

import com.travelmate.commons.exception.ClientRuntimeException;
import com.travelmate.commons.exception.ErrorCode;

public class UserEmailDuplicatedBadRequest extends ClientRuntimeException {
    public UserEmailDuplicatedBadRequest() {
        super(ErrorCode.USER_EMAIL_DUPLICATED_BAD_REQUEST);
    }
}
