package com.celticsengine.assetstore.models;

import com.celticsengine.assetstore.models.requests.CreateUserRequest;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class UserModel {
    private String asin = UUID.randomUUID().toString(); // TODO : check it does exist or no
    private String username;
    private String password;
    private String dateCreated = LocalDate.now().toString();

    public UserModel(String username, String password) {

        this.username = username;
        this.password = DigestUtils.sha512(password).toString();

    }

    public UserModel() {}

    public UserModel(UserModel.Builder builder) {
        this(builder().username, builder().password);

    }

    public String getAsin() {
        return asin;
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
        return getAsin().equals(userModel.getAsin()) && getUsername().equals(userModel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAsin(), getUsername());
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "asin='" + asin + '\'' +
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
