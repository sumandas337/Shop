package com.example.sumandas.shop.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.ui.PayActivity;
import com.example.sumandas.shop.ui.ProductActivity;

public class Navigator {

    public static void navigateToProductActivity(Activity activity, @NonNull String url){
        Intent intent = new Intent(activity, ProductActivity.class);
        intent.putExtra(ConstantUtils.PRODUCT_URL,url);
        activity.startActivity(intent);
    }

    public static void navigateToPayActivity(Activity activity, @NonNull Product product){
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(ConstantUtils.PRODUCT_OBJECT,product);
        activity.startActivity(intent);
    }


}
