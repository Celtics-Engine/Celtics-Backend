package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.dynamodb.CelticAssetsDao;
import com.celticsengine.assetstore.exception.InvalidAttributeValueException;
import com.celticsengine.assetstore.models.requests.AssetSearchRequest;
import com.celticsengine.assetstore.models.results.AssetSearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class AssetSearchActivity implements RequestHandler<AssetSearchRequest, AssetSearchResult> {
    private final Logger log = LogManager.getLogger();
    private final CelticAssetsDao celticAssetsDao;



    @Inject
    public AssetSearchActivity(CelticAssetsDao celticAssetsDao) {
        this.celticAssetsDao = celticAssetsDao;

    }


    @Override
    public AssetSearchResult handleRequest(final AssetSearchRequest assetSearchRequest, Context context) {
        log.info("Received AssetSearchRequest {}", assetSearchRequest);


        try {

            return AssetSearchResult.builder()
                    .withCelticAssetList(celticAssetsDao
                            .getCelticAssetsFromAssetsTable(assetSearchRequest.getName()))
                    .build();

        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
            return null;
        }
    }
}
