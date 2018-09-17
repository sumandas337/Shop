package com.example.sumandas.shop.application;

import com.example.sumandas.shop.net.RestApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RestApiModule.class})
public interface AppComponent extends IAppComponent {
}
