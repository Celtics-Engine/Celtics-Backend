package com.celticsengine.assetstore.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


public class CelticUsersDao {
    private final AmazonDynamoDB client;
    private final DynamoDBMapper dynamoDbMapper;


    public CelticUsersDao(AmazonDynamoDB amazonDynamoDB) {
        this.client = amazonDynamoDB;
        this.dynamoDbMapper = new DynamoDBMapper(client);
    }




    public CelticUsers getCelticUserFromUserName(String username) {

        Map<String, AttributeValue> names = new HashMap<>();

        names.put(":username",new AttributeValue().withS(username));

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("celtic_users")
                .withExpressionAttributeValues(names)
                .withFilterExpression("username = :username");


        ScanResult result = client.scan(scanRequest);


        if(result.getItems().stream().findFirst().isEmpty()){
            return null;
        }else {
            return dynamoDbMapper.load(CelticUsers.class,
                    result.getItems().stream().findFirst().get().get("user_id").getS());
        }
    }


    public CelticUsers getCelticUsers(String asin, String userName) {
        CelticUsers celticUsers = this.dynamoDbMapper.load(CelticUsers.class, asin, userName);
        if (celticUsers == null) {
            throw new CelticUsersNotFoundException("Could not find playlist with id " + asin + userName);
        }
        return celticUsers;
    }

    public CelticUsers saveCelticUsers(CelticUsers celticUsers) {
        this.dynamoDbMapper.save(celticUsers);
        return celticUsers;
    }
}
