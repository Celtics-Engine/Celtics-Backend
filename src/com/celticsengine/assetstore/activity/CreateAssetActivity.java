package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticAssetsDao;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticAsset;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.celticsengine.assetstore.models.requests.CreateAssetRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.util.UUID;

public class CreateAssetActivity implements RequestHandler<CreateAssetRequest, CreateAssetResult> {

    private final Logger log = LogManager.getLogger();
    private final CelticAssetsDao celticAssetsDao;
    private final CelticUsersDao celticUsersDao;


    @Inject
    public CreateAssetActivity(CelticAssetsDao celticAssetsDao, CelticUsersDao celticUsersDao) {
        this.celticAssetsDao = celticAssetsDao;
        this.celticUsersDao = celticUsersDao;
    }


    @Override
    public CreateAssetResult handleRequest(final CreateAssetRequest createAssetRequest, Context context) {
        log.info("Received CreateAssetRequest {}", createAssetRequest);

// Check if token is valid


        try {

            int i = createAssetRequest.getJwt().lastIndexOf('.');
            String withoutSignature = createAssetRequest.getJwt().substring(0, i+1);
            String userId = Jwts.parserBuilder().build().parseClaimsJwt(withoutSignature).getBody().getSubject();


            CelticUser celticUsers = celticUsersDao.getCelticUsers(userId);

            if (celticUsers != null) {
                Key key = Keys.hmacShaKeyFor(celticUsers.getPassword().getBytes(StandardCharsets.UTF_8));

                   Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(createAssetRequest.getJwt());
            } else {
                throw new CelticUsersNotFoundException("The userId does not exsit");
            }


            CelticAsset celticAsset = new CelticAsset();
            celticAsset.setUserId(userId);
            celticAsset.setAssetId(UUID.randomUUID().toString());
            celticAsset.setName(createAssetRequest.getName());
            celticAsset.setAssetLocation(celticAsset.getAssetLocation());
            celticAsset.setDescription(celticAsset.getDescription());
            celticAsset.setImages(celticAsset.getImages());
            celticAsset.setBucketId(celticAsset.getBucketId());
            celticAsset.setCompatibleEngineVer(celticAsset.getCompatibleEngineVer());
            celticAsset.setDatePosted(LocalDate.now().toString());

            celticAssetsDao.saveCelticAssets(celticAsset);


            return CreateAssetResult.builder()
                    .withUserId(celticAsset.getUserId())
                    .withAssetId(celticAsset.getAssetId())
                    .withName(celticAsset.getName())
                    .withAssetLocation(celticAsset.getAssetLocation())
                    .withDiscription(celticAsset.getDescription())
                    .withImages(celticAsset.getImages())
                    .withFileSize(celticAsset.getFileSize())
                    .withBucketId(celticAsset.getBucketId())
                    .withCompatableEngineVer(celticAsset.getCompatibleEngineVer())
                    .withDatePosted(celticAsset.getDatePosted())
                    .build();

            //OK, we can trust this JWT

        } catch (JwtException e) {
            e.printStackTrace();
            return null;

            //don't trust the JWT!
        }
    }
}