package com.example.sumandas.shop.ui.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.ShopApplication;
import com.example.sumandas.shop.databinding.FilterListBrandBinding;
import com.example.sumandas.shop.viewproviders.BrandFilterViewProvider;

import javax.inject.Inject;

public class BrandFilterFragment extends BaseBottomSheetDialogFragment {

    public static final String BRAND_TAG = "brand_fragment";

    private BrandFilterViewProvider brandFilterViewProvider;
    private View mView;

    public static BrandFilterFragment newInstance() {
        return new BrandFilterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        mView = View.inflate(getContext(), R.layout.filter_list_brand, null);
        dialog.setContentView(mView);
        FilterListBrandBinding filterListBrandBinding = DataBindingUtil.bind(mView);
        filterListBrandBinding.setBrandProvider(brandFilterViewProvider);
        return dialog;
    }


    public void setBrandFilterViewProvider(BrandFilterViewProvider brandFilterViewProvider) {
        this.brandFilterViewProvider = brandFilterViewProvider;

    }

    @Override
    public void onResume() {
        super.onResume();
        setDefaultBottomSheetBehaviorCallback(mView);
    }


}
