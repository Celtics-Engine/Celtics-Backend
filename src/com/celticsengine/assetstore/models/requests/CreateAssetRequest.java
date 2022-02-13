package com.celticsengine.assetstore.models.requests;

import org.checkerframework.checker.units.qual.C;

import java.util.Objects;
import java.util.Set;

public class CreateAssetRequest {

    private String jwt;
    private String name;
    private String descripsion;
    private Set<String> image;
    private String compatableEngineVer;

    public CreateAssetRequest(String jwt, String name, String descripsion,
                              Set<String> image, String compatableEngineVer) {
        this.jwt = jwt;
        this.name = name;
        this.descripsion = descripsion;
        this.image = image;
        this.compatableEngineVer = compatableEngineVer;
    }
    public CreateAssetRequest(){}

    public CreateAssetRequest(Builder builder) {this(builder.jwt, builder.name,
            builder.descripsion, builder.image, builder.compatableEngineVer);}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public Set<String> getImage() {
        return image;
    }

    public void setImage(Set<String> image) {
        this.image = image;
    }

    public String getCompatableEngineVer() {
        return compatableEngineVer;
    }

    public void setCompatableEngineVer(String compatableEngineVer) {
        this.compatableEngineVer = compatableEngineVer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAssetRequest that = (CreateAssetRequest) o;
        return getJwt().equals(that.getJwt()) && Objects.equals(getName(),
                that.getName()) && Objects.equals(getDescripsion(),
                that.getDescripsion()) && Objects.equals(getImage(),
                that.getImage()) && Objects.equals(getCompatableEngineVer(),
                that.getCompatableEngineVer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJwt(), getName(), getDescripsion(), getImage(),
                                                  getCompatableEngineVer());
    }

    public static final class Builder {
        private String jwt;
        private String name;
        private String descripsion;
        private Set<String> image;
        private String compatableEngineVer;

        private Builder() {}

        public Builder withJwt(String jwtToUse) {
            this.jwt = jwtToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withDescripsion(String descripsionToUse) {
            this.descripsion = descripsionToUse;
            return this;
        }

        public Builder withImage(Set<String> imageToUse) {
            this.image = imageToUse;
            return this;
        }

        public Builder withCompatableEngineVer(String compatableEngineVerToUse) {
            this.compatableEngineVer = compatableEngineVerToUse;
            return this;
        }

        public CreateAssetRequest build() { return new CreateAssetRequest(this);}
    }
}
