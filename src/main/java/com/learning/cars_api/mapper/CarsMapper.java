package com.learning.cars_api.mapper;


import com.learning.cars_api.dto.CarsDTO;
import com.learning.cars_api.entity.Cars;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarsMapper {


    CarsMapper INSTANCE = Mappers.getMapper(CarsMapper.class);


    default Cars toModel(CarsDTO carsDTO) {
        if (carsDTO == null) {
            return null;
        } else {
            Cars cars = new Cars();
            cars.setId(carsDTO.getId());
            cars.setName(carsDTO.getName());
            cars.setBrand(carsDTO.getBrand());
            cars.setYear(carsDTO.getYear());
            cars.setRare(carsDTO.isRare());

            return cars;
        }
    }

    default CarsDTO toDTO(Cars cars) {
        if (cars == null) {
            return null;
        } else {
            CarsDTO carsDTO = new CarsDTO();
            carsDTO.setId(cars.getId());
            carsDTO.setName(cars.getName());
            carsDTO.setBrand(cars.getBrand());
            carsDTO.setYear(cars.getYear());
            carsDTO.setRare(cars.isRare());

            return carsDTO;
        }
    }


}
