package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.celticsengine.assetstore.exception.InvalidAttributeValueException;
import com.celticsengine.assetstore.models.requests.UserLoginRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLoginActivity implements RequestHandler<UserLoginRequest, UserLoginResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticUsersDao celticUsersDao;
    private CelticUsers celticUsers;

    public UserLoginActivity(CelticUsersDao celticUsersDao) {
        this.celticUsersDao = celticUsersDao;
    }

    @Override
    public UserLoginResult handleRequest(UserLoginRequest userLoginRequest, Context context) {
        log.info("Requested UserLoginRequest {}", userLoginRequest);

        if (celticUsersDao.getCelticUserFromUserName(userLoginRequest.getUsername()) == null) {
            log.warn("Username Does Not Exist"); // FIXME: Possibility of crash (like in CreateUserActivity line 37)
            throw new CelticUsersNotFoundException("Username Does Not Exist");
        }

        celticUsers = celticUsersDao.getCelticUserFromUserName(userLoginRequest.getUsername());

        if (celticUsers.getPassword() != userLoginRequest.getPassword()) {
            log.warn("Invalid Password");
            celticUsers = null;
            throw new InvalidAttributeValueException("Invalid Password");
        }

        return UserLoginResult.builder().withCelticUser(celticUsers).build;
    }
}