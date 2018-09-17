package com.example.sumandas.shop.interactors;

import com.example.sumandas.shop.module.Module;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.net.RestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductInteractor {

    public static final String PRODUCT_KEY = "ProductGrid";



    public RestClient restClient;
    public Gson gson;

    @Inject
    public ProductInteractor(RestClient restClient, Gson gson) {
        this.restClient = restClient;
        this.gson = gson;
    }

    public void loadProducts(String path, String brandFilter, String sort, ICallback callback){
        restClient.getProduct(path, brandFilter, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Module>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoadError();
                    }

                    @Override
                    public void onNext(List<Module> modules) {
                        for(Module module: modules){
                            if(module.getName().equals(PRODUCT_KEY)){
                                ProductModule productModule = gson.fromJson(module.getData(),
                                        new TypeToken<ProductModule>(){}.getType());
                                callback.onProductLoaded(productModule);
                            }
                        };
                    }
                });
    }

}
