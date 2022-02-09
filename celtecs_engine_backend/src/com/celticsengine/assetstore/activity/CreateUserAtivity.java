package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.models.UserModel;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUserAtivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticUsersDao celticUsersDao;
    private final UserModel userModel = new UserModel();

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param celticUsersDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateUserAtivity(CelticUsersDao celticUsersDao) {
        this.celticUsersDao = celticUsersDao;
    }

    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createUserRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createUserResult result object containing the API defined {@link UserModel}
     */
    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        log.info("Received CreateUserRequest {}", createUserRequest);

        if (createUserRequest.getUsername().equals(celticUsersDao.getCelticUsers()))     {

        }

        CelticUsers celticUsers = new CelticUsers();
        celticUsers.setUsername(createUserRequest.getUsername());
        celticUsers.setPassword(createUserRequest.getPassword());


        celticUsersDao.saveCelticUsers(celticUsers);


        return CreateUserResult.builder()
                .withCelticUsers(new UserModel(createUserRequest.getUsername(),createUserRequest.getPassword()))
                .build();
    }
}
