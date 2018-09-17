package com.example.sumandas.shop.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sumandas.shop.databinding.SearchLayoutBinding;
import com.example.sumandas.shop.viewproviders.SearchViewProvider;
import com.example.sumandas.shop.R;

public class SearchFilterFragment extends Fragment {

    public static final String SEARCH_TAG = "search_fragment";


    private SearchViewProvider searchViewProvider;


    public static SearchFilterFragment newInstance() {
        return new SearchFilterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container,false);
        SearchLayoutBinding searchLayoutBinding = DataBindingUtil.bind(view);
        searchLayoutBinding.setSearchprovider(searchViewProvider);
        return searchLayoutBinding.getRoot();

    }

    public void setSearchViewProvider(SearchViewProvider searchViewProvider) {
        this.searchViewProvider = searchViewProvider;
    }



}
