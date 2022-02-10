package com.celticsengine.assetstore;

import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

public class Main {

	private static App app;


	public static void main(String[] args) {
		CreateUserRequest request = new CreateUserRequest("example@example.com", "password");

		CreateUserResult result = getApp().provideCreatePlaylistActivity().handleRequest(request, null);

		System.out.println(result.getJwt());

	}

	public static App getApp() {
		if (app == null) {
			app = new App();
		}

		return app;
	}
}
