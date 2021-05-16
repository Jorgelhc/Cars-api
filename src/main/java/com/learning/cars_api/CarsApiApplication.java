package com.learning.cars_api;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDynamoDBRepositories
public class CarsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsApiApplication.class, args);
        System.out.println("Cars_api");
    }

}
