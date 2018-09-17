package com.example.sumandas.shop.interactors;

import com.example.sumandas.shop.module.ProductModule;

public interface ICallback{
    void onProductLoaded(ProductModule productModule);
    void onLoadError();

}