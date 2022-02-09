package com.celticsengine.assetstore.models.results;

import com.celticsengine.assetstore.models.UserModel;

public class CreateUserResult {
    private UserModel userModel;

    public CreateUserResult(Builder builder) {
        this.userModel = builder.userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private UserModel userModel;

        public Builder withPlaylist(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public CreateUserResult build() {return new CreateUserResult(this);}
    }
}
