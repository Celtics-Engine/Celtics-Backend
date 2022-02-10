package com.celticsengine.assetstore.providers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.celticsengine.assetstore.Main.getApp;

public class CelticUsersDaoTest {

    private DynamoDBMapper mapper;
    private final AmazonDynamoDB client = new AmazonDynamoDBClient();

    private final String tableName = "celtic-users";
    Map<String, AttributeValue> names = new HashMap<>();


    @BeforeEach
    public void setUp() {
        this.mapper = new DynamoDBMapper(client);
    }

    @Test
    public void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("wetwork@wetwork.io");
        request.setPassword("test");




    }

    @Test
    void scanDbForAllUserNames() {
        List<CelticUsers> users = mapper.scan(CelticUsers.class,
                new DynamoDBScanExpression()
                        .withFilterExpression("username = :username")
                        .withExpressionAttributeValues(names));

        users.forEach(user -> System.out.println(user.getUsername()));
    }

}


