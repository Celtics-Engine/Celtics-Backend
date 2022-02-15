package com.celticsengine.assetstore.s3.bucket;

import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class TestFileUploadUrl {

	private static final Regions clientRegion = Regions.US_WEST_2;
	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();


	/**
	 * Generating a pre-signed URL is a completely offline operation (no API calls are involved),
	 * making it a very fast operation
	 *
	 * A pre-signed URL gives you access to the object identified in the URL (so the build.gradle file in this case)
	 */
	@Test
	void generatePresignedUrl() {
		String newFileName = "build.gradle";

		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
				"nonexsistantbucketboy",
				newFileName,
				HttpMethod.GET)
				.withExpiration(DateTime.now().plusMinutes(10).toDate());

		URL url = s3Client.generatePresignedUrl(request);

		System.out.println(url.toString());
	}
}
