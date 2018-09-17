package com.example.sumandas.shop.net;

import com.example.sumandas.shop.module.Module;

import java.util.List;

import rx.Observable;

public class RestClient {

    private RestApi mRestApi;

    public RestClient(RestApi restApi) {
        mRestApi = restApi;
    }

    public Observable<List<Module>> getModules() {
        return mRestApi.getModules();
    }

    public Observable<List<Module>> getProduct(String path, String brandFilter, String sort) {
        return mRestApi.getProducts(path, brandFilter, sort);
    }

    public Observable<List<Module>> getSearchResults(String path) {
        return mRestApi.getSearchResults(path);
    }
}