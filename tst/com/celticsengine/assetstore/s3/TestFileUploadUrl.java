package com.celticsengine.assetstore.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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

		String path = "uploads/new_folder_" + DateTime.now();
		String newFileName = "build.gradle";
		String fullFileName = path + "/" + newFileName;
		String fileContents = "This is an example file created through the Amazon S3 API.";

		s3Client.putObject(new PutObjectRequest("nonexsistantbucketboy", fullFileName, fileContents));


		URL url = s3Client.generatePresignedUrl("nonexsistantbucketboy",
				fullFileName,
				DateTime.now().plusMinutes(10).toDate());

		System.out.println(url.toString());
	}
}
