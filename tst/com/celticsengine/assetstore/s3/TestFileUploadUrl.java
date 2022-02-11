package com.celticsengine.assetstore.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class TestFileUploadUrl {

	private static final Regions clientRegion = Regions.US_WEST_2;
	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();



	@Test
	void testfunc(){
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
