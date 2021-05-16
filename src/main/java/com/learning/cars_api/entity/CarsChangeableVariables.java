package com.learning.cars_api.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CarsChangeableVariables {


    private String value;

    private Integer year;

    private Boolean rare;


}




