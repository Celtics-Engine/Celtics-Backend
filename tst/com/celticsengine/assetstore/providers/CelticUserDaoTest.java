package com.celticsengine.assetstore.providers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CelticUserDaoTest {

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
        List<CelticUser> users = mapper.scan(CelticUser.class,
                new DynamoDBScanExpression()
                        .withFilterExpression("username = :username")
                        .withExpressionAttributeValues(names));

        users.forEach(user -> System.out.println(user.getUsername()));
    }

}


