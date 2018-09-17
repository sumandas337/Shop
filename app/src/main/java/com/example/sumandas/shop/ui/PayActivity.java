package com.example.sumandas.shop.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.databinding.PayPageBinding;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.utils.ConstantUtils;
import com.example.sumandas.shop.viewmodels.IProductClicked;
import com.example.sumandas.shop.viewmodels.ProductItemModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends AppCompatActivity implements IProductClicked {

    @BindView(R.id.toolbar) Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Product product= getIntent().getParcelableExtra(ConstantUtils.PRODUCT_OBJECT);
        ProductItemModel productItemModel = new ProductItemModel(product, this);

        PayPageBinding payPageBinding= DataBindingUtil.setContentView(this, R.layout.pay_page );
        payPageBinding.setVm(productItemModel);

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



    }

    @Override
    public void onProductClicked(Product product) {
        //no op
    }

}
