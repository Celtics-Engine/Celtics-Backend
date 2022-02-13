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

    GeneratePresignedUrlRequest request;


    public PresignedUrlRequestModel(String bucketName,
                                    String objectKey, HttpMethod httpMethod, Date expiration) {

        this.httpMethod = httpMethod;
        this.expiration = expiration;
        this.request = new GeneratePresignedUrlRequest(bucketName, objectKey);
    }

    public URL getPresignedUrl() {
        request.setMethod(httpMethod);
        request.setExpiration(expiration);
        try {
            AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ProfileCredentialsProvider())
                    .build();
            return s3.generatePresignedUrl(request);
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
            throw new SdkClientException(e.getMessage(), e);
        }
    }

}
