package com.example.sumandas.shop.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.sumandas.shop.R;
import com.example.sumandas.shop.binding.IViewModel;
import com.example.sumandas.shop.binding.IViewProvider;
import com.example.sumandas.shop.model.Category;
import com.example.sumandas.shop.model.SubCategory;
import com.example.sumandas.shop.utils.StringUtils;
import com.example.sumandas.shop.viewproviders.CategoryViewModelProvider;

import java.util.ArrayList;

public class CategoryItemModel implements IViewModel, IViewProvider {

    private Category category;
    private CategoryViewModelProvider categoryViewModelProvider;

    public ObservableField<String> name = new  ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableInt noItems = new ObservableInt();
    public ObservableArrayList subCategoryVm = new ObservableArrayList();


    public CategoryItemModel(Category category, CategoryViewModelProvider categoryViewModelProvider) {
        this.category = category;
        this.categoryViewModelProvider = categoryViewModelProvider;
        name.set(category.name);
        imageUrl.set(StringUtils.unescapeJavaString(category.imageUrl));
        noItems.set(category.productCount);
        addSubCategories();
    }

    @Override
    public int getView(IViewModel vm) {
        return R.layout.sub_category;
    }

    public void addSubCategories(){
        ArrayList<SubCategoryItemModel> subCategoryItemModels= new ArrayList<>();
        for(SubCategory subCategory:category.subCategories){
            subCategoryItemModels.add(new SubCategoryItemModel(subCategory,this));
        }
        subCategoryVm.addAll(subCategoryItemModels);

    }

    public void onSubCategoryClicked(String url){
        categoryViewModelProvider.onSubCategoryClicked(url);
    }
}
