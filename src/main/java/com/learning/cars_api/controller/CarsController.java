package com.learning.cars_api.controller;


import com.learning.cars_api.dto.CarsChangeableVariables;
import com.learning.cars_api.dto.CarsDTO;
import com.learning.cars_api.service.CarsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final CarsService carsService;

    @GetMapping(CARS_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.OK)
    public Flux<CarsDTO> getAllItems() {
        log.info("requesting the list for all cars");
        return carsService.findAll();
    }

    @GetMapping(CARS_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<CarsDTO>> findById(@PathVariable Long id) {
        log.info("requesting car with id {}", id);
        return carsService.findByIdCar(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(CARS_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<CarsDTO> createCar(@RequestBody @Valid CarsDTO carsDTO) {
        log.info("a new car was created");
        return carsService.save(carsDTO);
    }

    @PostMapping(CARS_ENDPOINT_LOCAL + "/createFakeCars")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Flux<?> createFakeCars() {
        log.info("a new car was created");
        return carsService.createFakeCars();
    }

    @DeleteMapping(CARS_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        carsService.deleteById(id);
        log.info("Delete car with id {}", id);
    }

    @DeleteMapping(CARS_ENDPOINT_LOCAL + "/All")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        carsService.deleteAll();
        log.info("Delete all cars");
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsName")
    public Mono<ResponseEntity<CarsDTO>> modifyCarsName(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars name with id {}", id);
        return carsService.modifyCarsName(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsBrand")
    public Mono<ResponseEntity<CarsDTO>> modifyCarsBrand(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars brand with id {}", id);
        return carsService.modifyCarsBrand(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsYear")
    public Mono<ResponseEntity<CarsDTO>> modifyCarsYear(@PathVariable Long id, @RequestBody @Valid CarsChangeableVariables value) {
        log.info("modify cars year with id {}", id);
        return carsService.modifyCarsYear(id, value)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(CARS_ENDPOINT_LOCAL + "/{id}/modifyCarsRarity")
    public Mono<ResponseEntity<CarsDTO>> modifyCarsRarity(@PathVariable Long id) {
        log.info("modify cars rarity with id {}", id);
        return carsService.modifyCarsRarity(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
