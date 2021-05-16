package com.learning.cars_api.config;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

import static com.learning.cars_api.constants.CarsConstant.*;


public class CarsTable {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO)).build();

        DynamoDB dynamoDB = new DynamoDB((client));

        client.deleteTable(TABLE_NAME);

        try {

            Table table = dynamoDB.createTable(TABLE_NAME,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.N)),
                    new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
