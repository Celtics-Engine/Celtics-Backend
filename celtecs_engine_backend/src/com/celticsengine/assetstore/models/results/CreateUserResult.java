package com.celticsengine.assetstore.models.results;

import com.celticsengine.assetstore.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;


public class CreateUserResult {
    private String jwt;

    public CreateUserResult() {
    }
    public CreateUserResult(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static CreateUserResult.Builder builder() {return new Builder();}

    public static final class Builder {
        private String user_id;
        private String username;
        private String dateCreated;

        public Builder withUserId(String user_id){
            this.user_id = user_id;
            return this;
        }
        public Builder withUsername(String username){
            this.username = username;
            return this;
        }
        public Builder withDateCreated(String dateCreated){
            this.dateCreated = dateCreated;
            return this;
        }

        public CreateUserResult build(String passwordKey) {
            Key key = Keys.hmacShaKeyFor(passwordKey.getBytes(StandardCharsets.UTF_8));
            return new CreateUserResult(Jwts.builder()
                    .setSubject(user_id)
                    .claim("username", username)
                    .claim("date_created", dateCreated)
                    .signWith(key)
                    .compact());
        }
    }
}
