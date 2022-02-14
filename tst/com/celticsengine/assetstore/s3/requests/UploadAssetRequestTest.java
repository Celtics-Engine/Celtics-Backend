package com.celticsengine.assetstore.s3.requests;

import com.celticsengine.assetstore.s3.bucket.BucketAccess;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.celticsengine.assetstore.s3.requests.UploadAssetRequest.convertFileTypeToByteArray;

class UploadAssetRequestTest {

    @Test
    void uploadAsset() {
        final Path pathToAsset = Paths.get("",
                "src/resources/celtics-asset-test/asset.zip").toAbsolutePath();
        UploadAssetRequest uploadAssetRequest = new UploadAssetRequest();

        try {

            S3Presigner presigner = S3Presigner.create();
            String bucketName = "celtics-engine-asset-store";
            String keyName = "assets/asset.zip";
            String contentType = "application/zip";

            uploadAssetRequest.uploadAssetFromPresignedUrl(presigner, bucketName, keyName,
                    contentType,
                    convertFileTypeToByteArray(pathToAsset.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // check list of s3 objects to ensure the upload is in there
        BucketAccess.listObjects("celtics-engine-asset-store").contents().forEach(s3Object -> {
            System.out.println(s3Object.key());
        });
    }
}