package com.example.sumandas.shop.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.ShopApplication;
import com.example.sumandas.shop.databinding.ActivityMainBinding;
import com.example.sumandas.shop.utils.Navigator;
import com.example.sumandas.shop.viewproviders.CategoryViewModelProvider;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CategoryViewModelProvider.OnSubCategoryClicked {

    @Inject CategoryViewModelProvider categoryViewModelProvider;

    @BindView(R.id.toolbar) Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ShopApplication)getApplication()).getmApplicationComponent().
                injectMainActivity(this);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main );
        activityMainBinding.setCategoryModel(categoryViewModelProvider);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.shop_categories));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            mToolBar.setNavigationOnClickListener(v -> finish());
        }

        categoryViewModelProvider.loadCategories();

    }

    @Inject
    public void setUp(){
        categoryViewModelProvider.setCallBack(this);
    }

    @Override
    public void onSubCategoryClicked(String url) {
        Navigator.navigateToProductActivity(this,url);
    }
}
