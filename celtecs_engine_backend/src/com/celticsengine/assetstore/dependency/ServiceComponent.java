package com.celticsengine.assetstore.dependency;

import com.celticsengine.assetstore.activity.CreateUserAtivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {Mappermodule.class})
public interface ServiceComponent {
    CreateUserAtivity provideCreateUserActivity();

}