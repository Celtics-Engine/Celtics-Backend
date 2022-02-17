package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.UpdatePasswordRequest;
import com.celticsengine.assetstore.models.results.UpdatePasswordResult;

public class UpdatePasswordActivityProvider implements RequestHandler<UpdatePasswordRequest, UpdatePasswordResult> {

    private static App app;

    @Override
    public UpdatePasswordResult handleRequest(UpdatePasswordRequest updatePasswordRequest, Context context) {
        return getApp().provideUpdatePasswordActivityProvider().handleRequest(updatePasswordRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
