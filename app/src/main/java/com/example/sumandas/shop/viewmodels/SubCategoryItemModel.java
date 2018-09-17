package com.example.sumandas.shop.viewmodels;

import android.databinding.ObservableField;
import android.view.View;

import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.model.SubCategory;

public class SubCategoryItemModel implements IViewModel {

    private SubCategory subCategory;
    private CategoryItemModel categoryItemModel;

    public ObservableField<String> name = new ObservableField<>();

    public SubCategoryItemModel(SubCategory subCategory, CategoryItemModel categoryItemModel) {
        this.subCategory = subCategory;
        this.categoryItemModel = categoryItemModel;
        name.set(subCategory.name);
    }

    public void subCategoryClicked(View view){
        categoryItemModel.onSubCategoryClicked(subCategory.url);
    }

}
