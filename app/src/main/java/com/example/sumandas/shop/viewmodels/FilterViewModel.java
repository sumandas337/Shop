package com.example.sumandas.shop.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.IntDef;
import android.view.View;

import com.example.sumandas.shop.binding.IViewModel;


public class FilterViewModel implements IViewModel {

    @IntDef({FILTER_BRAND, FILTER_SORT})
    public @interface FilterType{
    }

    public static final int FILTER_BRAND = 1;
    public static final int FILTER_SORT = 2;


    public ObservableField<String> mValue = new ObservableField<>();
    public ObservableBoolean isApplied = new ObservableBoolean();

    public String slug;
    private IFilterCallback filterCallback;

    public FilterViewModel(String slug,String value , IFilterCallback filterCallback){
        this.slug = slug;
        this.filterCallback = filterCallback;
        mValue.set(value);
    }

    public void onFilterClicked(View view){
        filterCallback.onFilterAdded(slug ,isApplied.get());
    }

    public void onItemClicked(View view){
        boolean checked = !isApplied.get();
        isApplied.set(checked);
        filterCallback.onFilterAdded(slug ,isApplied.get());
    }


}
