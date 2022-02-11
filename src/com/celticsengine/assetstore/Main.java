package com.celticsengine.assetstore;

import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.requests.UserLoginRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

public class Main {

	private static App app;


	public static void main(String[] args) {
		CreateUserRequest request = new CreateUserRequest("example@example.com", "password");
		UserLoginRequest userLoginRequest = new UserLoginRequest("example@example.com", "password");

		//UserLoginResult result = getApp().provideCreatePlaylistActivity().handleRequest(request, null);

		UserLoginResult result = getApp().provideUserLoginActivityProvider().handleRequest(userLoginRequest,null);

		System.out.println(result.getJwt());

	}

	public static App getApp() {
		if (app == null) {
			app = new App();
		}

		return app;
	}
}
