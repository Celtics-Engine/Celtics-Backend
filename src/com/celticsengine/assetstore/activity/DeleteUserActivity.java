package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.celticsengine.assetstore.exception.InvalidAttributeValueException;
import com.celticsengine.assetstore.models.requests.DeleteUserRequest;
import com.celticsengine.assetstore.models.results.DeleteUserResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class DeleteUserActivity implements RequestHandler<DeleteUserRequest, DeleteUserResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticUsersDao celticUsersDao;

    public DeleteUserActivity(CelticUsersDao celticUsersDao) {
        this.celticUsersDao = celticUsersDao;
    }

    @Override
    public DeleteUserResult handleRequest(DeleteUserRequest deleteUserRequest, Context context) {
        log.info("Requested DeleteUserRequest {}", deleteUserRequest);

        int i = deleteUserRequest.getJwt().lastIndexOf('.');
        String withoutSignature = deleteUserRequest.getJwt().substring(0, i+1);
        String userId = Jwts.parserBuilder().build().parseClaimsJwt(withoutSignature).getBody().getSubject();
        
        try {
            CelticUser celticUser = celticUsersDao.getCelticUserScan(userId);

            if (celticUser == null) {
                log.warn("Invalid User Id {}", userId);
                throw new CelticUsersNotFoundException("The User Id Does Dot Exist");
            }

            if (deleteUserRequest.getPassword() == null || !celticUser.getPassword().equals(deleteUserRequest.getPassword())) {
                log.warn("Invalid Password {}", deleteUserRequest);
                throw new InvalidAttributeValueException("Invalid Password");
            }

            Key key = Keys.hmacShaKeyFor(celticUser.getPassword().getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(deleteUserRequest.getJwt());
            celticUsersDao.deleteCelticUser(celticUser);

            if (celticUsersDao.getCelticUserScan(userId) != null) {
                System.out.println("Error User Still Exists");
                log.error("Deleted User Still Exists {}", celticUser);
            }

        } catch (CelticUsersNotFoundException e) {
            log.error("Invalid User Id {}", userId);
        } catch (InvalidAttributeValueException e) {
            log.error("Invalid Password {}", e.getMessage());
        }

        return null;
    }
}
