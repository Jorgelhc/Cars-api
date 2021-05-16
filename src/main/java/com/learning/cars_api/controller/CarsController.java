package com.learning.cars_api.controller;


import com.learning.cars_api.entity.Cars;
import com.learning.cars_api.entity.CarsChangeableVariables;
import com.learning.cars_api.repository.CarsRepository;
import com.learning.cars_api.service.CarsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.learning.cars_api.constants.CarsConstant.CARS_ENDPOINT_LOCAL;


@RestController
@AllArgsConstructor
@Slf4j
public class CarsController {

    CarsService carsService;
    CarsRepository carsRepository;

    @GetMapping(CARS_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<Cars> getAllItems() {
        log.info("requesting the list for all cars");
        return carsService.findAll();
    }

    @GetMapping(CARS_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Cars>> findById(@PathVariable Long id) {
        log.info("requesting car with id {}", id);
        return carsService.findByIdCar(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(CARS_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Cars> createCar(@RequestBody @Valid Cars car) {
        log.info("a new car was created");
        return carsService.save(car);
    }

    @DeleteMapping(CARS_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        carsService.deleteById(id);
        log.info("Delete car with id {}", id);
    }


    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsName")
    public Mono<ResponseEntity<Cars>> modifyCarsName(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars name with id {}", id);
        return carsService.modifyCarsName(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsBrand")
    public Mono<ResponseEntity<Cars>> modifyCarsBrand(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars brand with id {}", id);
        return carsService.modifyCarsBrand(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsYear")
    public Mono<ResponseEntity<Cars>> modifyCarsYear(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars year with id {}", id);
        return carsService.modifyCarsYear(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsRarity")
    public Mono<ResponseEntity<Cars>> modifyCarsRarity(@PathVariable Long id) {
        log.info("modify cars rarity with id {}", id);
        return carsService.modifyCarsRarity(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
