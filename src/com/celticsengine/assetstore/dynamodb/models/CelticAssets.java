package com.celticsengine.assetstore.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "celtic_assets")
public class CelticAssets {

   private String userId;
   private String assetId;
   private String name ;
   private String assetLocation;
   private String discription;
   private Set<String> images;
   private String fileSize;
   private String bucketId;
   private String compatableEngineVer;
   private String datePosted;

   @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "asset_id")
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "asset_location")
    public String getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @DynamoDBAttribute(attributeName = "images")
    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    @DynamoDBAttribute(attributeName = "file_size")
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @DynamoDBAttribute(attributeName = "bucket_id")
    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    @DynamoDBAttribute(attributeName = "compatable_engine_ver")
    public String getCompatableEngineVer() {
        return compatableEngineVer;
    }

    public void setCompatableEngineVer(String compatableEngineVer) {
        this.compatableEngineVer = compatableEngineVer;
    }

    @DynamoDBAttribute(attributeName = "date_created")
    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
}
