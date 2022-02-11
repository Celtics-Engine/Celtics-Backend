package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.UserLoginRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

public class UserLoginActivityProvider implements RequestHandler<UserLoginRequest, UserLoginResult> {
	private static App app;

	public UserLoginActivityProvider() {

	}


	@Override
	public UserLoginResult handleRequest(UserLoginRequest userLoginRequest, Context context) {
		return getApp().provideUserLoginActivityProvider().handleRequest(userLoginRequest, context);
	}

	private App getApp() {
		if (app == null) {
			app = new App();
		}

		return app;
	}
}
