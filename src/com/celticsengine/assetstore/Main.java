package com.celticsengine.assetstore;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.dynamodb.CelticAssetsDao;
import com.celticsengine.assetstore.dynamodb.models.CelticAsset;
import com.celticsengine.assetstore.models.requests.AssetSearchRequest;
import com.celticsengine.assetstore.models.requests.CreateAssetRequest;
import com.celticsengine.assetstore.models.results.AssetSearchResult;
import com.celticsengine.assetstore.models.results.CreateAssetResult;

import java.util.HashSet;
import java.util.List;

public class Main {

    public static App app;


    public static void main(String[] args) {


//        CreateAssetRequest request = new CreateAssetRequest("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzE3NjNlNy05NTBkLTQyODQtYjY1Yi01MzY1OTE1ZWUxNzUiLCJ1c2VybmFtZSI6InRlc3RAZXhhbXBsZS5jb20iLCJkYXRlX2NyZWF0ZWQiOiIyMDIyLTAyLTE2IiwiZXhwIjoxNjQ1MTUyNjczfQ.5BWRc5500okYTtkFknb4rL79jJ-hYzYEdvpwfDCDBzeytggBRUtuZn1Ii5CK7CozIrqNwQC1rHehmvdWogIOQQ"
//                , "Will Cool Asset", "Test", new HashSet<>(List.of("")), "0.0.1");
//
////		CreateUserResult result = getApp().get.handleRequest(request,
////				null);
//        CreateAssetResult result = getApp().provideCreateAssetActivity().handleRequest(request, null);
//
//
//        System.out.println(result.toString());

        AssetSearchRequest request = new AssetSearchRequest("Cool Asset");
        AssetSearchResult result = getApp().provideAssetSearchActivity().handleRequest(request,null);

        for(CelticAsset celticAsset: result.getCelticAssetList()) {
            System.out.println("---->>>" + celticAsset.getAssetName());
        }
    }
//		Jws<Claims> claims = Jwts.parserBuilder().build().parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.ipevRNuRP6HflG8cFKnmUPtypruRC4fb1DWtoLL62SY");
//
//		System.out.println(claims.toString());
//
//		CelticUsersDao celticUsersDao = new CelticUsersDao(AmazonDynamoDBClientBuilder
//				.standard()
//				.withCredentials(new ProfileCredentialsProvider("default"))
//				.withRegion(Regions.US_WEST_2).build());
//		CelticUser user = celticUsersDao.getCelticUserFromUserName("example@example.com");
//		System.out.println(user.getUsername());


    //		System.out.println("hello");
//
//
//
//		CelticAssetsDao celticAssetsDao = new CelticAssetsDao(AmazonDynamoDBClientBuilder
//				.standard()
//				.withCredentials(new ProfileCredentialsProvider("default"))
//				.withRegion(Regions.US_WEST_2).build());
////
//		List<CelticAsset> celticAssets = celticAssetsDao.getCelticAssetsFromAssetsTable("IDHBG39788");
//
//		for (CelticAsset celticAsset : celticAssets) {
//			System.out.println("Vasue " + celticAsset.getAssetName());
//		}
//	}
    private static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }


}
