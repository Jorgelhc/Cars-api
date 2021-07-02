package com.learning.cars_api;

import com.learning.cars_api.builder.CarBuilderDTO;
import com.learning.cars_api.config.DynamoConfig;
import com.learning.cars_api.controller.CarsController;
import com.learning.cars_api.dto.CarsDTO;
import com.learning.cars_api.repository.CarsRepository;
import com.learning.cars_api.service.CarsService;
import io.netty.bootstrap.Bootstrap;
import mockwebserver3.MockWebServer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.webservices.client.AutoConfigureMockWebServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@SpringBootTest
class CarsApiApplicationTests {




    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private CarsService carsService;

    @Autowired
    CarsRepository carsRepository;

    @Test
    void getACarById() {

        CarsDTO carsDTO = CarBuilderDTO.builder().build().toDTO();

        Mockito.when(carsService.findByIdCar(carsDTO.getId())).thenReturn(Mono.just(carsDTO));


        webTestClient.get().uri("/api/v2/cars".concat("/{id}"), carsDTO.getId())
                .exchange().expectStatus().isOk();


    }


    @Test
    void getACarWithAnInvalidId() {

      CarsDTO carsDTO = CarBuilderDTO.builder().build().toDTO();

        Mockito.when(carsService.findByIdCar(carsDTO.getId())).thenReturn(Mono.empty());


        webTestClient.get().uri("/api/v2/cars".concat("/{id}"), carsDTO.getId())
                .exchange().expectStatus().isNotFound();

    }
//
//
//    @Test
//    void deleteCar() {
//
//
//        webTestClient.delete().uri(CarsConstant.CARS_ENDPOINT_LOCAL.concat("/{id}"), 1)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange().expectStatus().isNoContent().expectBody(Void.class);
//
//
//    }


}
