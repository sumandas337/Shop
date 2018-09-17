package com.example.sumandas.shop.viewmodels;

import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.module.ProductModule;

public interface IProductCallback {
    void onProductClicked(Product product);
    void onProductLoaded(ProductModule productModule);
}
