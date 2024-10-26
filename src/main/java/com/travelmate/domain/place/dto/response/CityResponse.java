package com.travelmate.domain.place.dto.response;


import com.travelmate.domain.place.domain.code.City;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityResponse {

    private Integer Id;
    private Integer code;
    private String name;


    public static CityResponse of(City city){
        return new CityResponse(city.getCityId(), city.getCode(), city.getName());
    }

}