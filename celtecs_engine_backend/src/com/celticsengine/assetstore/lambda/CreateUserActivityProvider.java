package com.celticsengine.assetstore.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {


//    private ServiceComponent dagger = DaggerServiceComponent.create();
//    private CreateUserRequest createPlaylistActivity = dagger.provideCreatePlaylistActivity();
//
//
    public CreateUserActivityProvider() {

    }

   @Override
    public CreateUserResult handleRequest(final CreateUserRequest createPlaylistRequest, Context context) {
        return createUserActivity.handleRequest(createPlaylistRequest, context);
    }


}

