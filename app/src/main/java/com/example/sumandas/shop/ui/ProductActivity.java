package com.example.sumandas.shop.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.ShopApplication;
import com.example.sumandas.shop.databinding.ProductMainBinding;
import com.example.sumandas.shop.interactors.ProductInteractor;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.module.Module;
import com.example.sumandas.shop.module.ProductModule;
import com.example.sumandas.shop.ui.fragment.BrandFilterFragment;
import com.example.sumandas.shop.ui.fragment.SearchFilterFragment;
import com.example.sumandas.shop.ui.fragment.SortFilterFragment;
import com.example.sumandas.shop.utils.ConstantUtils;
import com.example.sumandas.shop.utils.Navigator;
import com.example.sumandas.shop.utils.RxUtils;
import com.example.sumandas.shop.viewmodels.FilterViewModel;
import com.example.sumandas.shop.viewmodels.IFilterAdded;
import com.example.sumandas.shop.viewmodels.IProductCallback;
import com.example.sumandas.shop.viewproviders.BrandFilterViewProvider;
import com.example.sumandas.shop.viewproviders.ProductViewModelProvider;
import com.example.sumandas.shop.viewproviders.SearchViewProvider;
import com.example.sumandas.shop.viewproviders.SortFilterViewProvider;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity implements IProductCallback,
        IFilterAdded{

    @Inject ProductViewModelProvider productViewModelProvider;
    @Inject BrandFilterViewProvider brandFilterViewProvider;
    @Inject SortFilterViewProvider sortFilterViewProvider;
    @Inject SearchViewProvider searchViewProvider;

    @BindView(R.id.toolbar) Toolbar mToolBar;
    @BindView(R.id.et_search_box) EditText searchBox;
    @BindView(R.id.search_results_container) FrameLayout searchWidget;
    @BindView(R.id.search_results_widget)LinearLayout searchContainer;

    private String productPath;
    private String brandFilter;
    private String sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ShopApplication)getApplication()).getmApplicationComponent().
                injectProductActivity(this);

        productPath = getIntent().getStringExtra(ConstantUtils.PRODUCT_URL);

        ProductMainBinding productMainBinding = DataBindingUtil.setContentView(this, R.layout.product_main);
        productMainBinding.setProductProvider(productViewModelProvider);

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
        productViewModelProvider.loadProducts(productPath,brandFilter, sort);
        setUpSearchBox();

    }

    @Inject
    public void setUp(){
        productViewModelProvider.setCallBack(this);
        brandFilterViewProvider.setCallback(this);
        sortFilterViewProvider.setCallback(this);
        searchViewProvider.setCallBack(this);
    }


    @OnClick(R.id.btn_brand_filter)
    public void onBrandFilterClicked(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(BrandFilterFragment.BRAND_TAG);
        if (fragment == null) {
            fragment = BrandFilterFragment.newInstance();
            ((BrandFilterFragment)fragment).setBrandFilterViewProvider(brandFilterViewProvider);
            getSupportFragmentManager().beginTransaction().add(fragment, BrandFilterFragment.BRAND_TAG).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }

    @OnClick(R.id.btn_sort_filter)
    public void onSortFilterClicked(View view) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SortFilterFragment.SORT_TAG);
        if (fragment == null) {
            fragment = SortFilterFragment.newInstance();
            ((SortFilterFragment)fragment).setSortFilterViewProvider(sortFilterViewProvider);
            getSupportFragmentManager().beginTransaction().add(fragment, SortFilterFragment.SORT_TAG).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }

    public void showSearchResults(ProductModule productModule){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SearchFilterFragment.SEARCH_TAG);
        if (fragment == null) {
            fragment = SearchFilterFragment.newInstance();
            ((SearchFilterFragment)fragment).setSearchViewProvider(searchViewProvider);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.search_results_container, fragment,SearchFilterFragment.SEARCH_TAG)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
        searchContainer.setVisibility(View.VISIBLE);
        searchViewProvider.setProductList(productModule);
    }

    @OnClick(R.id.search_results_widget)
    public void onCloseSearchClicked(View view){
        searchContainer.setVisibility(View.GONE);
        searchBox.setText("");
    }



    @Override
    public void onProductClicked(Product product) {
        Navigator.navigateToPayActivity(this, product);
    }

    @Override
    public void onProductLoaded(ProductModule productModule) {
        sortFilterViewProvider.onSortLoaded(productModule);
        brandFilterViewProvider.onBrandsLoaded(productModule);
    }


    @Override
    public void onFilterAdded(@FilterViewModel.FilterType int type, String filter) {
        if(type == FilterViewModel.FILTER_SORT){
            sort = filter;

        }else if(type == FilterViewModel.FILTER_BRAND){
            brandFilter = filter;
        }
        productViewModelProvider.loadProducts(productPath,brandFilter, sort);

    }

    public void setUpSearchBox(){
        RxUtils.fromView(searchBox)
                .debounce(600, TimeUnit.MILLISECONDS)
                .filter(query -> !query.isEmpty())
                .distinctUntilChanged()
                .map(s -> s+"-s.json")
                .switchMap(s -> productViewModelProvider
                        .productInteractor
                        .restClient.getSearchResults(s))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Module>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Module> modules) {
                        for(Module module: modules){
                            if(module.getName().equals(ProductInteractor.PRODUCT_KEY)){
                                ProductModule productModule = productViewModelProvider
                                        .productInteractor.gson.fromJson(module.getData(),
                                        new TypeToken<ProductModule>(){}.getType());
                                showSearchResults(productModule);
                            }
                        };
                    }
                });
    }
}
