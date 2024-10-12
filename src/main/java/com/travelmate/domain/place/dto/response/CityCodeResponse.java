package com.travelmate.domain.place.dto.response;


import com.travelmate.domain.place.domain.code.CityCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityCodeResponse {

    private Integer Id;
    private Integer code;
    private String name;


    public static CityCodeResponse of(CityCode cityCode){
        return new CityCodeResponse(cityCode.getCityCodeId(), cityCode.getCode(), cityCode.getName());
    }

}