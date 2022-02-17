package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.GetAssetRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;


public class GetAssetActivityProvider implements RequestHandler<GetAssetRequest, CreateAssetResult> {

    private static App app;

    public GetAssetActivityProvider() {

    }

    @Override
    public CreateAssetResult handleRequest(final GetAssetRequest getAssetRequest, Context context) {
        return getApp().providerGetAssetActivity().handleRequest(getAssetRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }
}
