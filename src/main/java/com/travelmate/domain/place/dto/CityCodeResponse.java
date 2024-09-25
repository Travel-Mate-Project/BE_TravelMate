package com.travelmate.domain.place.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityCodeResponse {
    private Integer code;
    private String name;
}