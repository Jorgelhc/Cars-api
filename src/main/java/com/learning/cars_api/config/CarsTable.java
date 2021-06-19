package com.learning.cars_api.config;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.learning.cars_api.entity.Cars;

import static com.learning.cars_api.constants.CarsConstant.*;


public class CarsTable {


    public static void createTableIfNotExists() {


        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO)).build();

        DynamoDB dynamoDB = new DynamoDB((client));


        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);


        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Cars.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
        try {

            TableUtils.createTableIfNotExists(client, tableRequest);
            TableUtils.waitUntilActive(client, TABLE_NAME);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
