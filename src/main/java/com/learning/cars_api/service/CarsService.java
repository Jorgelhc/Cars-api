package com.learning.cars_api.service;


import com.learning.cars_api.dto.CarsChangeableVariables;
import com.learning.cars_api.dto.CarsDTO;
import com.learning.cars_api.entity.Cars;
import com.learning.cars_api.mapper.CarsMapper;
import com.learning.cars_api.repository.CarsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;
    private final CarsMapper carsMapper;

    public Flux<CarsDTO> findAll() {

        return Flux.fromIterable(carsRepository.findAll()).map(carsMapper::toDTO);

    }

    public Mono<CarsDTO> findByIdCar(Long id) {
        return Mono.justOrEmpty(carsRepository.findById(id)).map(carsMapper::toDTO);
    }

    public Mono<CarsDTO> save(CarsDTO carsDTO) {
        Cars cars = carsMapper.toModel(carsDTO);
        return Mono.justOrEmpty(carsMapper.toDTO(carsRepository.save(cars)));
    }

    public Mono<Boolean> deleteById(Long id) {

        carsRepository.deleteById(id);
        return Mono.just(true);
    }

    public Mono<CarsDTO> modifyCarsName(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setName(value.getValue());
        CarsDTO carsDTO = carsMapper.toDTO(carsRepository.save(cars));
        return Mono.just(carsDTO);

    }

    public Mono<CarsDTO> modifyCarsBrand(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setBrand(value.getValue());
        CarsDTO carsDTO = carsMapper.toDTO(carsRepository.save(cars));

        return Mono.just(carsDTO);

    }

    public Mono<CarsDTO> modifyCarsYear(Long id, CarsChangeableVariables value) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setYear(value.getYear());
        CarsDTO carsDTO = carsMapper.toDTO(carsRepository.save(cars));

        return Mono.just(carsDTO);

    }

    public Mono<CarsDTO> modifyCarsRarity(Long id) {

        Cars cars = carsRepository.findById(id).orElseThrow();
        cars.setRare(!cars.isRare());
        CarsDTO carsDTO = carsMapper.toDTO(carsRepository.save(cars));

        return Mono.just(carsDTO);

    }


}
