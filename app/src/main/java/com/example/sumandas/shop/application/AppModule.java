package com.example.sumandas.shop.application;

import com.example.sumandas.shop.utils.Navigator;
import com.example.sumandas.shop.utils.RxBus;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    RxBus providesRxBus(){
        return RxBus.getInstance();
    }

    @Singleton
    @Provides
    Navigator providesNavigator() {
        return new Navigator();
    }

    @Singleton
    @Provides
    Gson providesGson(){
        return new Gson();
    }

}
