package com.celticsengine.assetstore.dynamodb;

import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import com.celticsengine.assetstore.exception.CelticUsersNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

public class CelticUsersDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     */
    @Inject
    public CelticUsersDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link CelticUsers} corresponding to the specified id.
     *
     * @param id the Playlist ID
     * @return the stored Playlist, or null if none was found.
     */
    public CelticUsers getCelticUsers(String asin, String userName) {
        CelticUsers celticUsers = this.dynamoDbMapper.load(CelticUsers.class, asin, userName);

        if (celticUsers == null) {
            throw new CelticUsersNotFoundException("Could not find playlist with id " + asin + userName);
        }

        return celticUsers;
    }

    public CelticUsers saveCelticUsers(CelticUsers celticUsers) {

        this.dynamoDbMapper.save(celticUsers);
        return celticUsers;
    }
}
