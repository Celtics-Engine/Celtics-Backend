package com.celticsengine.assetstore.s3.requests;



import com.celticsengine.assetstore.s3.bucket.BucketAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.nio.file.Path;
import java.nio.file.Paths;

class PutAssetRequestTest {

    PutAssetRequest putAssetRequest;
    Region region = Region.US_WEST_2;
    private static S3Client s3;
    S3Presigner presigner = S3Presigner.create();
    String bucketName = "celtics-engine-asset-store";
    String keyName = "assets/asset.zip";
    String contentType = "application/zip";

    @BeforeEach
    void setUp() {
        putAssetRequest = new PutAssetRequest();
        s3 = S3Client.builder()
                .region(region)
                .build();
    }

    @Test
    void uploadAsset() {
        final Path pathToAsset = Paths.get("",
                "src/resources/celtics-asset-test/asset.zip").toAbsolutePath();

        PutAssetRequest putAssetRequest = new PutAssetRequest();

        try {

            putAssetRequest.uploadPresignedAsset(presigner, bucketName, keyName,
                    contentType,
                    pathToAsset.toFile());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // check list of s3 objects to ensure the upload is in there
        BucketAccess.listObjects("celtics-engine-asset-store").contents().forEach(s3Object -> {
            System.out.println(s3Object.key());
        });
    }

    @Test
    void uploadMultipleAssets() {

        final Path path1 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-asset-upload1.zip").toAbsolutePath();

        final Path path2 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-asset-upload2.zip").toAbsolutePath();

        final Path path3 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-upload.json").toAbsolutePath();

        putAssetRequest.multipartUpload(s3, bucketName,
                path1.toFile(), path2.toFile(), path3.toFile());


        // check list of s3 objects to ensure the upload is in there
        BucketAccess.listObjects("celtics-engine-asset-store").contents().forEach(s3Object -> {
            System.out.println(s3Object.key());
        });
    }

}