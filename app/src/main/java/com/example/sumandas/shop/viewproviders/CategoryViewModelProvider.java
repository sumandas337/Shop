package com.example.sumandas.shop.viewproviders;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.interactors.CategoryInteractor;
import com.example.sumandas.shop.model.Category;
import com.example.sumandas.shop.viewmodels.CategoryItemModel;
import com.example.sumandas.shop.viewmodels.IBaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryViewModelProvider implements IBaseModel,IViewProvider, CategoryInteractor.ICallback{

    private CategoryInteractor categoryInteractor;
    public ObservableArrayList categoryList = new ObservableArrayList();
    public ObservableInt numColumns = new ObservableInt();
    private OnSubCategoryClicked callback;

    @Inject
    public CategoryViewModelProvider(CategoryInteractor categoryInteractor){
        this.categoryInteractor = categoryInteractor;
        numColumns.set(2);
    }

    @Override
    public void onLoadError() {
        //no op
    }


    public void loadCategories(){
        categoryInteractor.loadCategories(this);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        ArrayList<CategoryItemModel> categoryItemModels = new ArrayList<>();
        for(Category category: categories){
            categoryItemModels.add(new CategoryItemModel(category, this));
        }
        categoryList.addAll(categoryItemModels);
    }

    @Override
    public int getView(IViewModel vm) {
        return R.layout.category;
    }

    public void setCallBack(OnSubCategoryClicked callBack){
        this.callback = callBack;
    }

    public void onSubCategoryClicked(String url){
        callback.onSubCategoryClicked(url);
    }


    public interface OnSubCategoryClicked{
        void onSubCategoryClicked(String url);
    }
}
