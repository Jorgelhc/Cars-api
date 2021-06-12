package com.learning.cars_api.mapper;


import com.learning.cars_api.dto.CarsDTO;
import com.learning.cars_api.entity.Cars;
import org.mapstruct.Mapper;

@Mapper
public interface CarsMapper {


    Cars toModel(CarsDTO carsDTO);

    CarsDTO toDTO(Cars cars);


}
