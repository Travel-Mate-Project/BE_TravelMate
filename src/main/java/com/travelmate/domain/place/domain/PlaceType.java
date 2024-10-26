package com.travelmate.domain.place.domain;

public enum PlaceType {

    ATTRACTION(39, "Attraction"),
    RESTAURANT(12, "Restaurant"),
    CAFE(13, "Cafe");

    private final int code;
    private final String description;

    PlaceType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }

    public static PlaceType fromCode(int code){
        for (PlaceType type : PlaceType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
