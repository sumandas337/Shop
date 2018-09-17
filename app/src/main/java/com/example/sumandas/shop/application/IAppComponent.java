package com.example.sumandas.shop.application;

import com.example.sumandas.shop.net.RestClient;
import com.example.sumandas.shop.ui.fragment.BrandFilterFragment;
import com.example.sumandas.shop.ui.MainActivity;
import com.example.sumandas.shop.ui.ProductActivity;
import com.example.sumandas.shop.ui.fragment.SortFilterFragment;
import com.example.sumandas.shop.utils.RxBus;

public interface IAppComponent {

    RestClient getRestClient();
    RxBus getRxBus();

    void injectMainActivity(MainActivity mainActivity);

    void injectProductActivity(ProductActivity productActivity);

    void injectBrandFragment(BrandFilterFragment brandFilterFragment);

    void injectSortFilterFragment(SortFilterFragment sortFilterFragment);


}


