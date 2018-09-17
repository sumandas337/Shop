package com.example.sumandas.shop.interactors;

import com.example.sumandas.shop.model.Category;
import com.example.sumandas.shop.module.CategoryModule;
import com.example.sumandas.shop.module.Module;
import com.example.sumandas.shop.net.RestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoryInteractor {

    public static final String  CATEGORY_KEY = "CategoryList";
    public static final String  CATEGORY_MAP_KEY = "categories";

    private RestClient restClient;
    private Gson gson;

    @Inject
    public CategoryInteractor(RestClient restClient, Gson gson) {
        this.restClient = restClient;
        this.gson = gson;
    }


    public void loadCategories(ICallback callback){
        restClient.getModules()
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
                            if(module.getName().equals(CATEGORY_KEY)){
                                CategoryModule categoryModule = gson.fromJson(module.getData(),
                                        new TypeToken<CategoryModule>(){}.getType());
                                callback.onCategoriesLoaded(categoryModule.categories);
                            }
                        }
                    }
                });
    }

    public interface ICallback{
        void onCategoriesLoaded(List<Category> categories);
        void onLoadError();

    }
}
