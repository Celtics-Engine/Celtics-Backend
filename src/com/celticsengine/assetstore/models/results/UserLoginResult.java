package com.celticsengine.assetstore.models.results;

import com.celticsengine.assetstore.dynamodb.models.CelticUsers;

public class UserLoginResult {
    private CelticUsers celticUsers;

    private UserLoginResult() {
    }

    private UserLoginResult(CelticUsers celticUsers) {
        this.celticUsers = celticUsers;
    }

    public CelticUsers getCelticUsers() {
        return celticUsers;
    }

    public static UserLoginResult.Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CelticUsers celticUsers;

        public Builder withCelticUsers(CelticUsers celticUsers) {
            this.celticUsers = celticUsers;
            return this;
        }

        public UserLoginResult build() {
            return new UserLoginResult(celticUsers);
        }
    }
}
