package com.celticsengine.assetstore.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;

import java.io.File;
import java.net.URL;
import java.util.List;

public class S3Dao {

    // TODO: Add lambda function to receive all the files from presigned url

    private final AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_2)
            .build();

    // we are using one bucket for all our assets (naming convention for bucket use dashes instead of underscores)
    private final String bucketName = "celtics-assets";

    //
    public S3Dao() {}

    private GeneratePresignedUrlRequest generatePresignedUrlRequest(String key) {
        return new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(HttpMethod.GET)
                .withExpiration(new java.util.Date(System.currentTimeMillis() + 3600000));
    }

    public URL getPresignedUrl(String key) {
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest(key));
    }

    public void uploadFile(File file, String key) {
        s3Client.putObject(bucketName, key, file);
    }

    public void downloadFile(String key, File file) {
        s3Client.getObject(new GetObjectRequest(bucketName, key), file);
    }

    public void deleteFile(String key) {
        s3Client.deleteObject(bucketName, key);
    }

    public List<Bucket> listBuckets() {
        return s3Client.listBuckets();
    }


}
