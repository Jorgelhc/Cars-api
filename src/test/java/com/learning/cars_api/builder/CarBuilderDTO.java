package com.learning.cars_api.builder;

import com.learning.cars_api.dto.CarsDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarBuilderDTO {

    @Builder.Default
    private Long id=2L;

    @Builder.Default
    private String name="F-250";

    @Builder.Default
    private String brand="Ford";

    @Builder.Default
    private int year=2018;

    @Builder.Default
    private boolean rare=false;

public CarsDTO toDTO(){
    return new CarsDTO(id, name,brand,year,rare);
}
}
