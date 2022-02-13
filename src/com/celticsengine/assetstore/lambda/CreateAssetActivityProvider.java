package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateAssetRequest;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;
import com.celticsengine.assetstore.models.results.CreateUserResult;

public class CreateAssetActivityProvider implements RequestHandler<CreateAssetRequest, CreateAssetResult> {

    private static App app;

    public CreateAssetActivityProvider() {

    }

    @Override
    public CreateAssetResult handleRequest(final CreateAssetRequest createAssetRequest, Context context) {
        return getApp().provideCreateAssetActivity().handleRequest(createAssetRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
