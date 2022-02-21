package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticAssetsDao;
import com.celticsengine.assetstore.dynamodb.models.CelticAsset;
import com.celticsengine.assetstore.exception.InvalidAttributeValueException;
import com.celticsengine.assetstore.models.requests.GetAssetRequest;
import com.celticsengine.assetstore.models.results.CreateAssetResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class GetAssetActivity implements RequestHandler<GetAssetRequest, CreateAssetResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticAssetsDao celticAssetsDao;


    @Inject
    public GetAssetActivity(CelticAssetsDao celticAssetsDao) {
        this.celticAssetsDao = celticAssetsDao;
    }


    @Override
    public CreateAssetResult handleRequest(final GetAssetRequest getAssetRequest, Context context) {
        log.info("Received GetAssetRequest {}", getAssetRequest);


        try {
            CelticAsset celticAsset = celticAssetsDao.getCelticAssets(getAssetRequest.getUserId(),
                    getAssetRequest.getAssetId());

            return CreateAssetResult.builder()
                    .withUserId(celticAsset.getUserId())
                    .withAssetId(celticAsset.getAssetId())
                    .withName(celticAsset.getAssetName())
                    .withAssetLocation(celticAsset.getAssetLocation())
                    .withDescription(celticAsset.getDescription())
                    .withImages(celticAsset.getImages())
                    .withFileSize(celticAsset.getFileSize())
                    .withBucketId(celticAsset.getBucketId())
                    .withCompatibleEngineVer(celticAsset.getCompatibleEngineVer())
                    .withDatePosted(celticAsset.getDatePosted())
                    .build();
        }
            catch (InvalidAttributeValueException e) {
                    e.printStackTrace();
                     return null;

    }
    }
}
