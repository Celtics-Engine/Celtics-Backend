package com.celticsengine.assetstore.s3.requests;



import com.celticsengine.assetstore.s3.bucket.BucketAccess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class PutGetDeleteAssetRequestTest {

    Region region = Region.US_WEST_2;
    private static S3Client s3;
    S3Presigner presigner = S3Presigner.create();
    String bucketName = "celtics-engine-asset-store";


    @BeforeEach
    void setUp() {
        s3 = S3Client.builder()
                .region(region)
                .build();
    }

    // check contents of bucket after each test
    @AfterEach
    void tearDown() {
        BucketAccess.listObjects("celtics-engine-asset-store").contents().forEach(s3Object -> {
            System.out.println(s3Object.key());
        });
        s3.close();
        presigner.close();
    }


    @Test
    void getPresignedAssetLink() {

        String keyName = "assets/asset.zip";
        PresignedGetObjectRequest presignedObject = GetAssetRequest.getPresignedAsset(presigner,
                bucketName, keyName);

        assert presignedObject != null;
        try {
            // note: just messing around here, this automatically opens the link in the browser
            // the important part here is it gives you a link
            java.awt.Desktop.getDesktop().browse(new URL(presignedObject.url().toString()).toURI());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    void uploadPresignedAsset() {

        String keyName = "assets/asset.zip";
        String contentType = "application/zip";

        final Path pathToAsset = Paths.get("",
                "src/resources/celtics-asset-test/asset.zip").toAbsolutePath();
        PutAssetRequest.uploadPresignedAsset(presigner, bucketName, keyName,
                contentType, pathToAsset.toFile());
    }

    @Test
    void uploadMultipleAssets() {

        final Path path1 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-asset-upload1.zip").toAbsolutePath();
        final Path path2 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-asset-upload2.zip").toAbsolutePath();
        final Path path3 = Paths.get("",
                "src/resources/celtics-asset-test/multiple-upload.json").toAbsolutePath();

        PutAssetRequest.multipartUpload(s3, bucketName,
                path1.toFile(), path2.toFile(), path3.toFile());
    }

    @Test
    void deleteAssets() {

        String keyName = "assets/asset.zip";
        DeleteAssetRequest.deleteBucketObjects(s3, bucketName, keyName);
    }

    // use this to delete all objects made in these tests
    @Test
    void deleteMultipleAssets() {

        String keyName1 = "assets/asset.zip";
        String keyName3 = "multiple-asset-upload1.zip";
        String keyName2 = "multiple-asset-upload2.zip";
        String keyName4 = "multiple-upload.json";


        ArrayList<ObjectIdentifier> objectIdentifierList = new ArrayList<>();
        ObjectIdentifier key1 = ObjectIdentifier.builder().key(keyName1).build();
        ObjectIdentifier key2 = ObjectIdentifier.builder().key(keyName2).build();
        ObjectIdentifier key3 = ObjectIdentifier.builder().key(keyName3).build();
        ObjectIdentifier key4 = ObjectIdentifier.builder().key(keyName4).build();

        objectIdentifierList.add(key1);
        objectIdentifierList.add(key2);
        objectIdentifierList.add(key3);
        objectIdentifierList.add(key4);


        DeleteAssetRequest.deleteMultipleBucketObjects(s3, bucketName, objectIdentifierList);
    }

}