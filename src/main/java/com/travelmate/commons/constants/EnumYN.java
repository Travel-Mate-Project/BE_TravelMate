package com.travelmate.commons.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumYN {
    Y("Y"),
    N("N"),
    ;
    private final String code;

    public static EnumYN of(final boolean booleanValue) {
        if (booleanValue) {
            return Y;
        }
        return N;
    }

    public String getCode() {
        return this.code;
    }
}
