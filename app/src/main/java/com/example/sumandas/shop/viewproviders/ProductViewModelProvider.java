package com.example.sumandas.shop.viewproviders;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.interactors.ICallback;
import com.example.sumandas.shop.interactors.ProductInteractor;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.utils.StringUtils;
import com.example.sumandas.shop.viewmodels.IBaseModel;
import com.example.sumandas.shop.viewmodels.IProductCallback;
import com.example.sumandas.shop.viewmodels.IProductClicked;
import com.example.sumandas.shop.viewmodels.ProductItemModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProductViewModelProvider implements IBaseModel,ICallback, IViewProvider,IProductClicked {

    public ProductInteractor productInteractor;
    private IProductCallback callback;

    public ObservableInt numColumns = new ObservableInt();
    public ObservableArrayList productList = new ObservableArrayList();

    @Inject
    public ProductViewModelProvider(ProductInteractor productInteractor) {
        this.productInteractor = productInteractor;
        numColumns.set(2);
    }

    public void loadProducts(String path, String brandFilter, String sort){
        productInteractor.loadProducts(StringUtils.replacePhpToJson(path),brandFilter,sort,this);
    }

    @Override
    public void onProductLoaded(ProductModule productModule) {
        ArrayList<ProductItemModel> productItemModels = new ArrayList<>();
        for(Product product: productModule.products){
            productItemModels.add(new ProductItemModel(product, this));
        }
        productList.clear();
        productList.addAll(productItemModels);
        callback.onProductLoaded(productModule);
    }

    @Override
    public void onLoadError() {

    }

    @Override
    public int getView(IViewModel vm) {
        return R.layout.product;
    }


    public void setCallBack(IProductCallback callBack){
        this.callback = callBack;
    }


    @Override
    public void onProductClicked(Product product){
        callback.onProductClicked(product);
    }


}
