package com.celticsengine.assetstore.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import com.celticsengine.assetstore.models.results.CreateUserResult;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreateUserAtivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private final ModelConverter modelConverter = new ModelConverter();

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateUserActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
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
     * @param createPlaylistRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link UserModel}
     */
    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createPlaylistRequest, Context context) {
        log.info("Received CreatePlaylistRequest {}", createUserRequest);

        if (!MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getName())
                || !MusicPlaylistServiceUtils.isValidString(createPlaylistRequest.getCustomerId())) {
            throw new InvalidAttributeValueException("Please recheck your userName or CustomerID.");
        }

        Playlist playlist = new Playlist();
        playlist.setCustomerId(createPlaylistRequest.getCustomerId());
        playlist.setName(createPlaylistRequest.getName());
        playlist.setTags(new HashSet<>(createPlaylistRequest.getTags()));
        playlist.setId(MusicPlaylistServiceUtils.generatePlaylistId());
        playlist.setSongList(new ArrayList<>());
        playlist.setSongCount(0);

        playlistDao.savePlaylist(playlist);


        return CreatePlaylistResult.builder()
                .withPlaylist(modelConverter.toPlaylistModel(playlist))
                .build();
    }
}
