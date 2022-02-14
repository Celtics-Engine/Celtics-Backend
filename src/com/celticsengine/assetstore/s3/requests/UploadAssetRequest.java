package com.celticsengine.assetstore.s3.requests;

import com.amazonaws.HttpMethod;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.http.SdkHttpRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class UploadAssetRequest {

    /**
     * this is only necessary if we want the user to be able to upload
     * bucket stuff other than compressed zip files (assets)
     *
     * As of now, the assets are to be stored in zip format, but if we want a user to
     * be able to store a profile pic for instance, then we can just reuse this same upload asset class
     *
     * the conversion of the object to be uploaded into bytes first
     * will allow for anything to be stored on the bucket
     *
     *
     * @param filePath file path to object to be uploaded
     * @return conversion into byte array of that object to be used in the upload asset method
     */
    public static byte[] convertFileTypeToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public void uploadAssetFromPresignedUrl(S3Presigner presigner, String bucketName, String keyName,
                                            String contentType, byte[] file) {
        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .contentType(contentType)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

            System.out.println("Which HTTP method: " +
                    presignedRequest.httpRequest().method());

            // Upload content to the Amazon S3 bucket by using this URL
            URL url = presignedRequest.url();
            System.out.println("Presigned URL to upload a file to: " + url);

            // Create the connection and use it to upload the new object by using the presigned URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestMethod("PUT");
            connection.getOutputStream().write(file);
            connection.getResponseCode();
            System.out.println("HTTP response code is " + connection.getResponseCode());
            connection.disconnect();

        } catch (S3Exception e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
