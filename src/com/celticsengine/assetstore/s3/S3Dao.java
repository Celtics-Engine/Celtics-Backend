package com.celticsengine.assetstore.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.util.Date;

public class S3Dao {

    private final AmazonS3 client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_2)
            .build();

    private final String bucketName = "celtics-engine-asset-store";

    public S3Dao() {}

    private GeneratePresignedUrlRequest generatePresignedUrlRequest(String key) {
        return new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(new Date(System.currentTimeMillis() + 3600000));
    }

    private GeneratePresignedUrlRequest generatePresignedUrlPutRequest(String key) {
        return new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(new Date(System.currentTimeMillis() + 3600000));
    }
}
