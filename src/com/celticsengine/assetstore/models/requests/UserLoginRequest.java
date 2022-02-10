package com.celticsengine.assetstore.models.requests;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;
import java.util.Objects;

public class UserLoginRequest {
    private String username;
    private String password;

    public UserLoginRequest(String username, String password) {
        this.username = username;
        this.password = Base64.getEncoder().encodeToString(DigestUtils.sha512(password));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginRequest that = (UserLoginRequest) o;
        return getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
