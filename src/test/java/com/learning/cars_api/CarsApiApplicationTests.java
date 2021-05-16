package com.learning.cars_api;

import com.learning.cars_api.constants.CarsConstant;
import com.learning.cars_api.repository.CarsRepository;
import com.learning.cars_api.service.CarsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class CarsApiApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Mock
    @Autowired
    CarsRepository carsRepository;

    @InjectMocks
    CarsService carsService;

    @Test
    void getACarById() {


        webTestClient.get().uri(CarsConstant.CARS_ENDPOINT_LOCAL.concat("/{id}"), 2)
                .exchange().expectStatus().isOk().expectBody();


    }


    @Test
    void getACarWithAnInvalidId() {

        webTestClient.get().uri(CarsConstant.CARS_ENDPOINT_LOCAL.concat("/{id}"), 99)
                .exchange().expectStatus().isNotFound();


    }


    @Test
    void deleteCar() {


        webTestClient.delete().uri(CarsConstant.CARS_ENDPOINT_LOCAL.concat("/{id}"), 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isNoContent().expectBody(Void.class);


    }


}
