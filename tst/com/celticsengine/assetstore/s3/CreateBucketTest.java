package com.celticsengine.assetstore.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class CreateBucketTest {

    private static final Regions clientRegion = Regions.US_WEST_2;
    private final Logger log = LogManager.getLogger();
    String bucketName = "celtics-asset-store-bucket-test";

    AmazonS3 s3Client;


    @BeforeEach
    void setUp() {
        try {
            s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();
            System.out.println("S3 client is built.");
            System.out.printf("%n");
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @AfterEach
    public void tearDown() {
        s3Client.shutdown();
    }

    @Test
    void testCreateBucket() {
        Bucket b = null;
        if (s3Client.doesBucketExistV2(bucketName)) {
            System.out.format("Bucket %s already exists.\n", bucketName);
            b = getBucket();
        } else {
            try {
                b = s3Client.createBucket(bucketName);
                log.info(String.format("Bucket %s created.\n", bucketName));
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }


        assert b == null || b.getName().equals(bucketName);

        System.out.println(Objects.requireNonNull(b).getName());
    }

    Bucket getBucket() {
        Bucket named_bucket = null;
        List<Bucket> buckets = s3Client.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(this.bucketName)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    // http request to upload into bucket

}

