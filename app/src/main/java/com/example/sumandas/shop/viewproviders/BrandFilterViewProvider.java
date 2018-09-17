package com.example.sumandas.shop.viewproviders;

import android.databinding.ObservableArrayList;
import android.text.TextUtils;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.model.BrandFilter;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.viewmodels.FilterViewModel;
import com.example.sumandas.shop.viewmodels.IFilterAdded;
import com.example.sumandas.shop.viewmodels.IFilterCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BrandFilterViewProvider implements IViewProvider, IFilterCallback {

    private IFilterAdded callback;
    private List<String> filteredBrands= new ArrayList<>();
    public ObservableArrayList brandList = new ObservableArrayList();
    private boolean isLoaded = false;

    @Inject
    public BrandFilterViewProvider(){

    }

    @Override
    public int getView(IViewModel vm) {
        return R.layout.filter_name_checkbox_item;
    }

    public void setCallback(IFilterAdded callback) {
        this.callback = callback;
    }

    public void onBrandsLoaded(ProductModule productModule){
        if(isLoaded){
            return;
        }
        isLoaded = true;
        List<FilterViewModel> vms = new ArrayList<>();
        for(BrandFilter brandFilter: productModule.brandFilters){
            vms.add(new FilterViewModel(brandFilter.slug, brandFilter.name,this));
        }
        brandList.clear();
        brandList.addAll(vms);
    }

    @Override
    public void onFilterAdded(String slug, boolean isChecked){
        if(isChecked){
            filteredBrands.add(slug);
        }else{
            filteredBrands.remove(slug);
        }
        callback.onFilterAdded(FilterViewModel.FILTER_BRAND,TextUtils.join(",",filteredBrands));
    }


}
