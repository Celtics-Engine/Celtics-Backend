package com.celticsengine.assetstore.s3.requests;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Date;

import static com.amazonaws.HttpMethod.*;

/**
 * The presigned URLs are valid only for the specified duration
 *
 * If you created a presigned URL using a temporary token,
 * then the URL expires when the token expires,
 * even if the URL was created with a later expiration time.
 *
 *     setup:
 *     needs security creds
 *     bucket name
 *     object key
 *     Http method (GET for instance would download the object from S3)
 *     expiration date and time
 *
 */
public class PresignedUrlRequestModel {

    private final Logger log = LogManager.getLogger();
    private final HttpMethod httpMethod;
    private final Date expiration;
    AmazonS3 s3Client;
    GeneratePresignedUrlRequest request;


    public PresignedUrlRequestModel(AmazonS3 s3Client, String bucketName,
                                    String objectKey, HttpMethod httpMethod, Date expiration) {
        this.s3Client = s3Client;
        this.httpMethod = httpMethod;
        this.expiration = expiration;
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        this.request = new GeneratePresignedUrlRequest(bucketName, objectKey);
    }

    // TODO: still needs to be tested
    private URL getPresignedRequestUrl() {
        try {
            switch (httpMethod) {
                case GET:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(GET)
                            .withExpiration(this.expiration));
                case POST:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(POST)
                            .withExpiration(this.expiration));
                case PUT:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(PUT)
                            .withExpiration(this.expiration));
                case DELETE:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(DELETE)
                            .withExpiration(this.expiration));
                case HEAD:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(HEAD)
                            .withExpiration(this.expiration));
                case PATCH:
                    return s3Client.generatePresignedUrl(request
                            .withMethod(PATCH)
                            .withExpiration(this.expiration));
            }

            log.info("Presigned URL: " + request.toString());

        } catch (SdkClientException e) {
            log.error("Error creating presigned URL", e);
            e.printStackTrace();
        }

        return null;
    }

}
