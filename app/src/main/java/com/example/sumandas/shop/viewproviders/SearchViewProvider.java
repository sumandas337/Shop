package com.example.sumandas.shop.viewproviders;

import android.databinding.ObservableArrayList;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.viewmodels.IProductCallback;
import com.example.sumandas.shop.viewmodels.IProductClicked;
import com.example.sumandas.shop.viewmodels.ProductItemModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchViewProvider implements IViewProvider, IProductClicked{


    public ObservableArrayList searchList = new ObservableArrayList();
    private IProductCallback callback;


    @Override
    public int getView(IViewModel vm) {
        return R.layout.product;
    }


    @Inject
    public SearchViewProvider(){

    }


    public void setProductList(ProductModule productModule){
        ArrayList<ProductItemModel> productItemModels = new ArrayList<>();
        for(Product product: productModule.products){
            productItemModels.add(new ProductItemModel(product, this));
        }
        searchList.clear();
        searchList.addAll(productItemModels);

    }

    public void setCallBack(IProductCallback callBack){
        this.callback = callBack;
    }

    @Override
    public void onProductClicked(Product product){
        callback.onProductClicked(product);
    }

}
