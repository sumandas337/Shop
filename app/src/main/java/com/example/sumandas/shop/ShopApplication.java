package com.example.sumandas.shop;

import android.support.multidex.MultiDexApplication;

import com.example.sumandas.shop.application.DaggerAppComponent;
import com.example.sumandas.shop.application.IAppComponent;
import com.example.sumandas.shop.binding.BindingUtils;

public class ShopApplication extends MultiDexApplication {

    protected IAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        BindingUtils.setDefaultBinder(BindingUtils.getDefaultBinder());
    }


    public void initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    public IAppComponent getmApplicationComponent() {
        return mAppComponent;
    }

}
