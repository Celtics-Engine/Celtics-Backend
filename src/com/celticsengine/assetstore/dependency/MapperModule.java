package com.celticsengine.assetstore.dependency;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.celticsengine.assetstore.dynamodb.providers.DynamoDbClientProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MapperModule {

	@Singleton
	@Provides
	public DynamoDBMapper provideDynamoDBMapper() {
		return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2));
	}
}

