package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, UserLoginResult> {


	private static App app;

	public CreateUserActivityProvider() {

	}

	@Override
	public UserLoginResult handleRequest(final CreateUserRequest createPlaylistRequest, Context context) {
		return getApp().provideCreateUserActivity().handleRequest(createPlaylistRequest, context);
	}

	private App getApp() {
		if (app == null) {
			app = new App();
		}

		return app;
	}


}

