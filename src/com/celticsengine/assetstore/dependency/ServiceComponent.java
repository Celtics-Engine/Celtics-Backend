package com.celticsengine.assetstore.dependency;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {Mappermodule.class})
public interface ServiceComponent {
	//CreateUserAtivity provideCreateUserActivity();

}