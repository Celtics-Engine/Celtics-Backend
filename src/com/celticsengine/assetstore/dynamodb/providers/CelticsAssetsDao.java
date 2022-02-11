package com.celticsengine.assetstore.dynamodb.providers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.celticsengine.assetstore.dynamodb.models.CelticAssets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO:
public class CelticsAssetsDao {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    DynamoDBMapper mapper;

    public CelticsAssetsDao() {
         mapper = new DynamoDBMapper(client);
    }

    public void save(CelticAssets asset) {
        mapper.save(asset);
    }

    public void delete(String user_id, CelticAssets asset) {
        mapper.delete(mapper.load(CelticAssets.class, user_id, asset.getAssetId()));
    }

    public CelticAssets getAsset(String user_id, String asset_id) {
        return mapper.load(CelticAssets.class, user_id, asset_id);
    }

    public CelticAssets getUserId(String user_id) {
        return mapper.load(CelticAssets.class, user_id);
    }
    public void update(CelticAssets asset) {
        mapper.save(asset);
    }

    // scanExpression could be used to get all assets for a user
    public void deleteAll() {
        mapper.batchDelete(mapper.scan(CelticAssets.class, null));
    }

    public void deleteAllAssetsFromUser(String user_id) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":user_id", new AttributeValue().withS(user_id));
        mapper.batchDelete(mapper.scan(CelticAssets.class,
                new DynamoDBScanExpression()
                        .withFilterExpression("user_id = :user_id")
                        .withExpressionAttributeValues(eav)));

    }

}
