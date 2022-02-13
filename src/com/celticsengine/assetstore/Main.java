package com.celticsengine.assetstore;

import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateAssetRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;

import java.util.HashSet;
import java.util.List;

public class Main {

	private static App app;


	public static void main(String[] args) {
		CreateAssetRequest request = new CreateAssetRequest("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1ZWUwZTI2OC1hMzQxLTQwZTktYmFlNC0yYWM4NTU2ZjU2NTUiLCJ1c2VybmFtZSI6InRlc3RAZXhhbXBsZS5jb20iLCJkYXRlX2NyZWF0ZWQiOiIyMDIyLTAyLTExIiwiZXhwIjoxNjQ0NzA3MDQyfQ.GuVIt0fR_tjVXRxJQgKtcf2dwB-KO0MH-iwoZ8USiJRJRR9EljNyvUkaQjbA56jJY2Xjed54KLQ13dKd-3UQzA"
	,"Will", "Test", new HashSet<>(List.of("")), "0.0.1");

//		CreateUserResult result = getApp().get.handleRequest(request,
//				null);
		CreateAssetResult result = getApp().provideCreateAssetActivity().handleRequest(request, null);


		System.out.println(result.toString());
//		Jws<Claims> claims = Jwts.parserBuilder().build().parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.ipevRNuRP6HflG8cFKnmUPtypruRC4fb1DWtoLL62SY");
//
//		System.out.println(claims.toString());
	}

	public static App getApp() {
		if (app == null) {
			app = new App();
		}

		return app;
	}
}
