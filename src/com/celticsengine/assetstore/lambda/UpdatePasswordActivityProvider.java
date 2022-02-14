package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.UpdatePasswordRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

public class UpdatePasswordActivityProvider implements RequestHandler<UpdatePasswordRequest, UserLoginResult> {

    private static App app;

    @Override
    public UserLoginResult handleRequest(UpdatePasswordRequest updatePasswordRequest, Context context) {
        return getApp().provideUpdatePasswordActivityProvider().handleRequest(updatePasswordRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
