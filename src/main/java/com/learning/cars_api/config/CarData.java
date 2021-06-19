package com.learning.cars_api.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.learning.cars_api.constants.CarsConstant;
import reactor.core.publisher.Flux;

import static com.learning.cars_api.constants.CarsConstant.TABLE_NAME;

public class CarData {



        public static Flux<PutItemOutcome> createFakeCars() {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(CarsConstant.ENDPOINT_DYNAMO, CarsConstant.REGION_DYNAMO)).build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable(TABLE_NAME);
        Item car = new Item()
                .withPrimaryKey("id", 1)
                .withString("name", "Gol")
                .withString("brand", "volkswagen")
                .withNumber("year", 2010)
                .withBoolean("rare", false);


        Item car2 = new Item()
                .withPrimaryKey("id", 2)
                .withString("name", "Uno")
                .withString("brand", "Fiat")
                .withNumber("year", 2000)
                .withBoolean("rare", false);

        Item car3 = new Item()
                .withPrimaryKey("id", 3)
                .withString("name", "Fox")
                .withString("brand", "volkswagen")
                .withNumber("year", 2015)
                .withBoolean("rare", false);

        Item car4 = new Item()
                .withPrimaryKey("id", 4)
                .withString("name", "Mustang Shelby")
                .withString("brand", "Ford")
                .withNumber("year", 1999)
                .withBoolean("rare", true);

         return   Flux.just(table.putItem(car),table.putItem(car2),table.putItem(car3),table.putItem(car4));

    }
}
