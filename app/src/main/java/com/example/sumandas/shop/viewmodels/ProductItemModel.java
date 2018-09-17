package com.example.sumandas.shop.viewmodels;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.view.View;

import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.utils.StringUtils;

public class ProductItemModel implements IViewModel {

    private Product product;
    private IProductClicked productClicked;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableFloat price = new ObservableFloat();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> brand = new ObservableField<>();
    public ObservableField<String> discountP = new ObservableField<>();
    public ObservableField<String> discountPrice = new ObservableField<>();

    public ProductItemModel(Product product, IProductClicked productClicked) {
        this.product = product;
        this.productClicked = productClicked;
        name.set(product.variant.fullName);
        imageUrl.set(StringUtils.unescapeJavaString(product.variant.images.get(0)));
        price.set(product.variant.mrp);
        brand.set(product.brand.name);
        float discount = product.variant.discount/product.variant.mrp*100;
        discountP.set(String.format ("%.1f", discount)+"%");
        discountPrice.set(Float.toString(product.variant.mrp- product.variant.discount));
    }


    public void productClicked(View view){
        productClicked.onProductClicked(product);
    }

}
