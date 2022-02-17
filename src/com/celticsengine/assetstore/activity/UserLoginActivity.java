package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.celticsengine.assetstore.exception.InvalidAttributeValueException;
import com.celticsengine.assetstore.models.requests.UserLoginRequest;
import com.celticsengine.assetstore.models.results.UserLoginResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLoginActivity implements RequestHandler<UserLoginRequest, UserLoginResult> {
	private final Logger log = LogManager.getLogger();
	private final CelticUsersDao celticUsersDao;

	public UserLoginActivity(CelticUsersDao celticUsersDao) {
		this.celticUsersDao = celticUsersDao;
	}

	@Override
	public UserLoginResult handleRequest(UserLoginRequest userLoginRequest, Context context) {
		log.info("Requested UserLoginRequest {}", userLoginRequest);

		try {
			CelticUser celticUser = celticUsersDao.getCelticUserFromUserName(userLoginRequest.getUsername());

			if (celticUser == null) {
				log.warn("Invalid Username", userLoginRequest);
				throw new CelticUsersNotFoundException("Invalid Username");
			}

			if (userLoginRequest.getPassword() == null || !celticUser.getPassword().equals(userLoginRequest.getPassword())) {
				log.warn("Invalid Password");
				throw new InvalidAttributeValueException("Invalid Password");
			}

			return UserLoginResult.builder()
					.createFromCelticUser(celticUser)
					.build(celticUser.getPassword());

		} catch (CelticUsersNotFoundException e) {
			log.error("Invalid Username", e.getMessage());

		} catch (InvalidAttributeValueException e) {
			log.error("Invalid Password", e.getMessage());

		}

		return null;
	}
}
