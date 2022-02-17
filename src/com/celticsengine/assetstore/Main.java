package com.celticsengine.assetstore;

import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateAssetRequest;
import com.celticsengine.assetstore.models.requests.GetAssetRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;

import java.util.HashSet;
import java.util.List;

public class Main {

	private static App app;


	public static void main(String[] args) {
		GetAssetRequest request = new GetAssetRequest();
		request.setUserId("IDHBG39788");
		request.setAssetId("B019HKJTCI");

//		CreateUserResult result = getApp().get.handleRequest(request,
//				null);
		CreateAssetResult result = getApp().providerGetAssetActivity().handleRequest(request, null);


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
