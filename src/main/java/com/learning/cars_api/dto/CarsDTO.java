package com.learning.cars_api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarsDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String brand;

    @NotNull
    private int year;

    @NotNull
    private boolean rare;

}
