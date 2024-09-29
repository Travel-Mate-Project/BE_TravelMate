package com.travelmate.domain.place.exception;

import com.travelmate.commons.exception.ClientRuntimeException;
import com.travelmate.commons.exception.ErrorCode;

public class CourseInvalidDateException extends ClientRuntimeException {
    public CourseInvalidDateException() {
        super(ErrorCode.INVALID_COURSE_DATE);
    }
}
