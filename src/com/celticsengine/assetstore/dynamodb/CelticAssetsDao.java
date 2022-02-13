package com.celticsengine.assetstore.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.celticsengine.assetstore.dynamodb.models.CelticAssets;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CelticAssetsDao {

    private final AmazonDynamoDB defaultClient = AmazonDynamoDBClientBuilder.defaultClient();
    private final AmazonDynamoDB client;
    private final DynamoDBMapper dynamoDbMapper;


    public CelticAssetsDao(AmazonDynamoDB amazonDynamoDB) {
        this.client = amazonDynamoDB;
        this.dynamoDbMapper = new DynamoDBMapper(client);
    }

    public void load(CelticUsers celticUsers) {
        this.dynamoDbMapper.load(celticUsers);
    }

    public CelticUsers getCelticAssetsFromUserName(String username) {

        Map<String, AttributeValue> names = new HashMap<>();

        names.put(":name",new AttributeValue().withS(username));

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("celtic_assets")
                .withExpressionAttributeValues(names)
                .withFilterExpression("name = :name");


        ScanResult result = client.scan(scanRequest);


        if(result.getItems().stream().findFirst().isEmpty()){
            return null;
        }else {
            return dynamoDbMapper.load(CelticUsers.class,
                    result.getItems().stream().findFirst().get().get("user_id").getS());
        }
    }

    // probably doesn't work
    public CelticAssets getCelticAssetsScan(String id) {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("CelticUsers")
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(new HashMap<String, AttributeValue>() {{
                    put(":id", new AttributeValue().withS(id));
                }});

        ScanResult scanResult = client.scan(scanRequest);

        if (scanResult.getCount() == 0) {
            throw new CelticUsersNotFoundException(id);
        }

        return dynamoDbMapper.marshallIntoObject(CelticAssets.class, scanResult.getItems().get(0));
    }


    public CelticAssets getCelticAssets(String userId, String assertId) {
        CelticAssets celticAsssets = this.dynamoDbMapper.load(CelticAssets.class, userId, assertId);
        if (celticAsssets == null) {
            throw new CelticUsersNotFoundException("Could not find playlist with id " + userId + assertId);
        }
        return celticAsssets;
    }

    public void saveCelticAssets(CelticAssets celticAsssets) {
        this.dynamoDbMapper.save(celticAsssets);
    }

}
