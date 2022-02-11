package com.celticsengine.assetstore.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Set;

@DynamoDBTable(tableName = "celtic_assets")
public class CelticAssets {

   private String userId;
   private String assetId;
   private String name ;
   private String assetLocation;
   private String description;
   private Set<String> images;
   private double fileSize;
   private String downloads;
   private String compatibleEngineVer;
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "images")
    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    @DynamoDBAttribute(attributeName = "file_size")
    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    @DynamoDBAttribute(attributeName = "downloads")
    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    @DynamoDBAttribute(attributeName = "compatible_engine_ver")
    public String getCompatibleEngineVer() {
        return compatibleEngineVer;
    }

    public void setCompatibleEngineVer(String compatibleEngineVer) {
        this.compatibleEngineVer = compatibleEngineVer;
    }

    @DynamoDBAttribute(attributeName = "date_created")
    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public S3Link assetImage;

    @DynamoDBAttribute(attributeName = "asset_image")
    public S3Link getProductImage() {
        return assetImage;
    }

    public void setProductImage(S3Link assetImage) {
        this.assetImage = assetImage;
    }
}
