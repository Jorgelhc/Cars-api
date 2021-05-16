package com.learning.cars_api.repository;

import com.learning.cars_api.entity.Cars;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface CarsRepository extends CrudRepository<Cars, Long> {


}
