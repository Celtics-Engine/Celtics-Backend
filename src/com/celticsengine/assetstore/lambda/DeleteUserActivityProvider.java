package com.celticsengine.assetstore.lambda;

import com.amazonaws.DefaultRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.DeleteUserRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

public class DeleteUserActivityProvider implements RequestHandler<DeleteUserRequest, UserLoginResult> {

    private static App app;

    public DeleteUserActivityProvider() {
    }

    @Override
    public UserLoginResult handleRequest(DeleteUserRequest deleteUserRequest, Context context) {
        return getApp().provideDeleteUserActivity().handleRequest(deleteUserRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
