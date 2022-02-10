package com.celticsengine.assetstore.models;

import java.time.LocalDate;
import java.util.Objects;

public class UserModel {
    private String userId;
    private String username;
    private String password;
    private String dateCreated = LocalDate.now().toString();

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserModel() {}

    public UserModel(UserModel.Builder builder) {
        this(builder().username, builder().password);
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getDateCreated() {
        return dateCreated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return getUserId().equals(userModel.getUserId()) && getUsername().equals(userModel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername());
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "asin='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }

    public static UserModel.Builder builder() { return new UserModel.Builder(); }

    public static final class Builder {
        private String username;
        private String password;

        private Builder() {

        }

        public UserModel.Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public UserModel.Builder withPassword(String passwordToUse) {
            this.password = passwordToUse;
            return this;
        }

        public UserModel build() { return new UserModel(this); }
    }
}
