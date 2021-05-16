package com.learning.cars_api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import static com.learning.cars_api.constants.CarsConstant.TABLE_NAME;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DynamoDBTable(tableName = TABLE_NAME)
public class Cars {

    @Id
    @DynamoDBHashKey(attributeName = "id")
    private Long id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "brand")
    private String brand;

    @DynamoDBAttribute(attributeName = "year")
    private int year;

    @DynamoDBAttribute(attributeName = "rare")
    private boolean rare;

}
