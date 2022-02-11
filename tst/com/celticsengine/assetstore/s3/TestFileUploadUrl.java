package com.celticsengine.assetstore.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class TestFileUploadUrl {

	private static final Regions clientRegion = Regions.US_WEST_2;
	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();


	/**
	 * Generating a pre-signed URL is a completely offline operation (no API calls are involved),
	 * making it a very fast operation
	 *
	 * A pre-signed URL gives you access to the object identified in the URL (so the build.gradle file in this case)
	 *
	 *
	 *
	 */
	@Test
	void generatePresignedUrl_retrievalOfBucketObject(){
	String newFileName = "build.gradle";

		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
				"nonexsistantbucketboy",
				newFileName,
				HttpMethod.PUT)
				.withExpiration(DateTime.now().plusMinutes(10).toDate());

		URL url = s3Client.generatePresignedUrl(request);

		System.out.println(url.toString());
	}
}
