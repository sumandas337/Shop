package com.example.sumandas.shop.viewproviders;

import android.databinding.ObservableArrayList;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.viewmodels.FilterViewModel;
import com.example.sumandas.shop.viewmodels.IFilterAdded;
import com.example.sumandas.shop.viewmodels.IFilterCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class SortFilterViewProvider implements IViewProvider,IFilterCallback{

    private String appliedSort ;
    private IFilterAdded callback;
    public ObservableArrayList sortList = new ObservableArrayList();
    public List<FilterViewModel> vms ;
    private boolean isLoaded = false;

    @Inject
    public SortFilterViewProvider(){

    }

    @Override
    public int getView(IViewModel vm) {
        return R.layout.filter_name_checkbox_item;
    }

    public void setCallback(IFilterAdded callback) {
        this.callback = callback;
    }

    @Override
    public void onFilterAdded(String slug, boolean isChecked){
        appliedSort = isChecked ? slug : null;
        if(isChecked){
            for(FilterViewModel filterViewModel: vms){
                if(!filterViewModel.slug.equals(slug) && filterViewModel.isApplied.get()){
                    filterViewModel.isApplied.set(false);
                }
            }
        }

        callback.onFilterAdded(FilterViewModel.FILTER_SORT,appliedSort);
    }


    public void onSortLoaded(ProductModule productModule){
        if(isLoaded){
            return;
        }
        isLoaded = true;
        vms = new ArrayList<>();
        HashMap<String, String> sortMap = productModule.sortOptions.options;
        for(String key: sortMap.keySet()){
            vms.add(new FilterViewModel(key, sortMap.get(key),this));
        }
        sortList.clear();
        sortList.addAll(vms);
    }


}
