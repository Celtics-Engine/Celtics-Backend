package com.celticsengine.assetstore.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;

import java.util.HashMap;
import java.util.Map;


public class CelticUsersDao {
	private final AmazonDynamoDB client;
	private final DynamoDBMapper dynamoDbMapper;


	public CelticUsersDao(AmazonDynamoDB amazonDynamoDB) {
		this.client = amazonDynamoDB;
		this.dynamoDbMapper = new DynamoDBMapper(client);
	}

	public void load(CelticUser celticUser) {
		this.dynamoDbMapper.load(celticUser);
	}

	public CelticUser getCelticUserFromUserName(String username) {

		Map<String, AttributeValue> names = new HashMap<>();

		names.put(":username", new AttributeValue().withS(username));

		ScanRequest scanRequest = new ScanRequest()
				.withTableName("celtic_users")
				.withExpressionAttributeValues(names)
				.withFilterExpression("username = :username");


		ScanResult result = client.scan(scanRequest);


		if (result.getItems().stream().findFirst().isEmpty()) {
			return null;
		} else {
			return dynamoDbMapper.load(CelticUser.class,
					result.getItems().stream().findFirst().get().get("user_id").getS());
		}
	}

	// probably doesn't work
	public CelticUser getCelticUserScan(String id) {
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

		return dynamoDbMapper.marshallIntoObject(CelticUser.class, scanResult.getItems().get(0));
	}


	public CelticUser getCelticUsers(String asin, String userName) {
		CelticUser celticUser = this.dynamoDbMapper.load(CelticUser.class, asin, userName);
		if (celticUser == null) {
			throw new CelticUsersNotFoundException("Could not find playlist with id " + asin + userName);
		}
		return celticUser;
	}

	public void saveCelticUsers(CelticUser celticUser) {
		this.dynamoDbMapper.save(celticUser);
	}

	public void deleteCelticUser(CelticUser celticUser) {
		this.dynamoDbMapper.delete(celticUser);
	}

}
