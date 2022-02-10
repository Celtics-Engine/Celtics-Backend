package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dependency.App;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {


    private static App app;

    public CreateUserActivityProvider() {

    }

    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createPlaylistRequest, Context context) {
        return getApp().provideCreatePlaylistActivity().handleRequest(createPlaylistRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }

        return app;
    }


}

