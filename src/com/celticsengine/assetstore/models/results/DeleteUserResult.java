package com.celticsengine.assetstore.models.results;

public class DeleteUserResult {
    private boolean userWasDeleted;
    private String jwt;
    private String dateDeleted;

    public DeleteUserResult() {
    }

    public DeleteUserResult(Builder builder) {
        this.userWasDeleted = builder.userWasDeleted;
        this.jwt = builder.jwt;
        this.dateDeleted = builder.dateDeleted;
    }

    public boolean getUserWasDeleted() {
        return userWasDeleted;
    }

    public void setUserWasDeleted(boolean userWasDeleted) {
        this.userWasDeleted = userWasDeleted;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(String dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public static DeleteUserResult.Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean userWasDeleted;
        private String jwt;
        private String dateDeleted;

        public Builder withUserWasDeleted(boolean userWasDeleted) {
            this.userWasDeleted = userWasDeleted;
            return this;
        }

        public Builder withJwt(String jwt) {
            this.jwt = jwt;
            return this;
        }

        public Builder withDateDeleted(String  dateDeleted) {
            this.dateDeleted = dateDeleted;
            return this;
        }

        public DeleteUserResult build() {
            return new DeleteUserResult(this);
        }
    }
}
