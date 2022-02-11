package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticAssetsDao;
import com.celticsengine.assetstore.dynamodb.models.CelticAssets;
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


    @Inject
    public CreateAssetActivity(CelticAssetsDao celticAssetsDao) {
        this.celticAssetsDao = celticAssetsDao;
    }


    @Override
    public CreateAssetResult handleRequest(final CreateAssetRequest createAssetRequest, Context context) {
        log.info("Received CreateAssetRequest {}", createAssetRequest);

// Check if token is valid
        try {
            if (Jwts.parserBuilder().build().parseClaimsJws(createAssetRequest.getJwt()) != null) {
                // grab jwt
                // verify that JWT belongs to the user
                // take the user id from JWT
                // grab from DAO saved last user
                // re create JWT
                // check if they are the same

            };

            //OK, we can trust this JWT

        } catch (JwtException e) {

            //don't trust the JWT!
        }


        CelticAssets celticAssets = new CelticAssets();
        celticAssets.setUserId( Jwts.parserBuilder().build().parseClaimsJws(createAssetRequest
                                                    .getJwt()).getBody().getSubject());
        celticAssets.setAssetId(UUID.randomUUID().toString());
        celticAssets.setName(createAssetRequest.getName());
        celticAssets.setAssetLocation(celticAssets.getAssetLocation());
        celticAssets.setDiscription(celticAssets.getDiscription());
        celticAssets.setImages(celticAssets.getImages());
        celticAssets.setBucketId(celticAssets.getBucketId());
        celticAssets.setCompatableEngineVer(celticAssets.getCompatableEngineVer());
        celticAssets.setDatePosted(LocalDate.now().toString());


        celticAssetsDao.saveCelticAssets(celticAssets);


        return CreateAssetResult.builder()
                .withUserId(celticAssets.getUserId())
                .withAssetId(celticAssets.getAssetId())
                .withName(celticAssets.getName())
                .withAssetLocation(celticAssets.getAssetLocation())
                .withDiscription(celticAssets.getDiscription())
                .withImages(celticAssets.getImages())
                .withFileSize(celticAssets.getFileSize())
                .withBucketId(celticAssets.getBucketId())
                .withCompatableEngineVer(celticAssets.getCompatableEngineVer())
                .withDatePosted(celticAssets.getDatePosted())
                .build();

    }

}
