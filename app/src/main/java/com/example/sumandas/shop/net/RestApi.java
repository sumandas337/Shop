package com.example.sumandas.shop.net;

import com.example.sumandas.shop.module.Module;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestApi {

    @GET("/module.json")
    Observable<List<Module>> getModules();

    @GET("products/{path}")
    Observable<List<Module>> getProducts(@Path("path") String path,
                                              @Query("brands") String brand,
                                              @Query("sort") String sort);


    @GET("search/{path}")
    Observable<List<Module>> getSearchResults(@Path("path") String path);

}
