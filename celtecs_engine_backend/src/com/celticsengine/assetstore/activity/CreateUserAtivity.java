package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.exception.InvalidAttributeException;
import com.celticsengine.assetstore.models.UserModel;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.UUID;

public class CreateUserAtivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticUsersDao celticUsersDao;
    private final UserModel userModel = new UserModel();


    @Inject
    public CreateUserAtivity(CelticUsersDao celticUsersDao) {
        this.celticUsersDao = celticUsersDao;
    }


    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        log.info("Received CreateUserRequest {}", createUserRequest);

        if (celticUsersDao.getCelticUserFromUserName(createUserRequest.getUsername()) != null)     {
            throw new InvalidAttributeException("User Already Exist"); //FIXME: this should be something else
        }

        CelticUsers celticUsers = new CelticUsers();
        celticUsers.setUserId(UUID.randomUUID().toString());
        celticUsers.setUsername(createUserRequest.getUsername());
        celticUsers.setPassword(createUserRequest.getPassword());
        celticUsers.setDateCreated(LocalDate.now().toString());


        celticUsersDao.saveCelticUsers(celticUsers);


        return CreateUserResult.builder()
                .withCelticUsers(new UserModel(createUserRequest.getUsername(),createUserRequest.getPassword()))
                .build();
    }
}
