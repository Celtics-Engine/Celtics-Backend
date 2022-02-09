package com.celticsengine.assetstore.models.requests;

import java.util.Objects;

public class CreateUserRequest {

    private String username;
    private String password;

    public CreateUserRequest(String asin, String username, String password, String dateCreated) {
        this.username = username;
        this.password = password;
    }

    public CreateUserRequest() {
    }

    public CreateUserRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return getUsername().equals(that.getUsername()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String username;
        private String password;

        private Builder() {

        }

        public Builder withUsername(String usernameToUse) {
            this.username = usernameToUse;
            return this;
        }

        public Builder withPassword(String passwordToUse) {
            this.password = passwordToUse;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(this);
        }
    }
}
