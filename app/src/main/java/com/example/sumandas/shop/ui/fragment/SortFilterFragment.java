package com.example.sumandas.shop.ui.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.databinding.FilterListSortBinding;
import com.example.sumandas.shop.viewproviders.SortFilterViewProvider;

public class SortFilterFragment extends BaseBottomSheetDialogFragment {

    public static final String SORT_TAG = "sort_fragment";

    private SortFilterViewProvider sortFilterViewProvider;
    private View mView;

    public static SortFilterFragment newInstance() {
        return new SortFilterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        mView = View.inflate(getContext(), R.layout.filter_list_sort, null);
        dialog.setContentView(mView);
        FilterListSortBinding filterListSortBinding = DataBindingUtil.bind(mView);
        filterListSortBinding.setSortProvider(sortFilterViewProvider);
        return dialog;
    }

    public void setSortFilterViewProvider(SortFilterViewProvider sortFilterViewProvider) {
        this.sortFilterViewProvider = sortFilterViewProvider;

    }

    @Override
    public void onResume() {
        super.onResume();
        setDefaultBottomSheetBehaviorCallback(mView);
    }

}
