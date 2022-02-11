package com.celticsengine.assetstore.s3;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import java.io.File;

public class S3Dao {

    AmazonDynamoDB client;

    public S3Dao(AmazonDynamoDB client) {
        this.client = client;
    }

    public void uploadFile(File file) {

    }

    public void downloadFile(String fileName) {

    }

    public void deleteFile(String fileName) {

    }

    public void listFiles() {

    }

    public String getS3Url(String fileName, String type) {
        switch (type) {
            case "image":
                return "https://s3.amazonaws.com/celtic_assets/images/" + fileName;
            case "audio":
                return "https://s3.amazonaws.com/celtic_assets/audio/" + fileName;
            case "video":
                return "https://s3.amazonaws.com/celtic_assets/video/" + fileName;
            default:
                return "https://s3.amazonaws.com/celtic_assets/" + fileName;
        }
    }


}
