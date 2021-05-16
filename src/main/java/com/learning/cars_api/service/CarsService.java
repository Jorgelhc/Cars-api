package com.learning.cars_api.service;


import com.learning.cars_api.entity.CarsChangeableVariables;
import com.learning.cars_api.entity.Cars;
import com.learning.cars_api.repository.CarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;


    public Flux<Cars> findAll() {

        return Flux.fromIterable(this.carsRepository.findAll());

    }

    public Mono<Cars> findByIdCar(Long id) {
        return Mono.justOrEmpty(this.carsRepository.findById(id));
    }

    public Mono<Cars> save(Cars cars) {
        return Mono.justOrEmpty(this.carsRepository.save(cars));
    }

    public Mono<Boolean> deleteById(Long id) {

        carsRepository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<Cars> modifyCarsName(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setName(value.getValue());

        return Mono.just(carsRepository.save(cars));

    }

    public Mono<Cars> modifyCarsBrand(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setBrand(value.getValue());

        return Mono.just(carsRepository.save(cars));

    }

    public Mono<Cars> modifyCarsYear(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setYear(value.getYear());

        return Mono.just(carsRepository.save(cars));

    }

    public Mono<Cars> modifyCarsRarity(Long id) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setRare(!cars.isRare());

        return Mono.just(carsRepository.save(cars));

    }


}
