package com.celticsengine.assetstore.models.results;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class CreateAssetResult {

    private String jwt;

    public CreateAssetResult() {
    }
    public CreateAssetResult(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static CreateUserResult.Builder builder() {return new CreateUserResult.Builder();}

    public static final class Builder {
        private String user_id;
        private String username;
        private String dateCreated;

        public CreateAssetResult.Builder withUserId(String user_id){
            this.user_id = user_id;
            return this;
        }
        public CreateAssetResult.Builder withUsername(String username){
            this.username = username;
            return this;
        }
        public CreateAssetResult.Builder withDateCreated(String dateCreated){
            this.dateCreated = dateCreated;
            return this;
        }

        public CreateAssetResult build(String passwordKey) {
            Key key = Keys.hmacShaKeyFor(passwordKey.getBytes(StandardCharsets.UTF_8));
            return new CreateAssetResult(Jwts.builder()
                    .setSubject(user_id)
                    .claim("username", username)
                    .claim("date_created", dateCreated)
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(key)
                    .compact());
        }
    }
}
