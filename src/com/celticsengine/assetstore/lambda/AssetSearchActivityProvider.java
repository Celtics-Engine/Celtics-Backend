package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.AssetSearchRequest;
import com.celticsengine.assetstore.models.results.AssetSearchResult;

public class AssetSearchActivityProvider implements RequestHandler<AssetSearchRequest, AssetSearchResult> {
    private static App app;

    public AssetSearchActivityProvider() {}

    @Override
    public AssetSearchResult handleRequest(final AssetSearchRequest assetSearchRequest, Context context) {
        return getApp().provideAssetSearchActivity().handleRequest(assetSearchRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
