package com.example.sumandas.shop.module;

import com.example.sumandas.shop.model.BrandFilter;
import com.example.sumandas.shop.model.Product;
import com.example.sumandas.shop.model.SortOptions;
import com.example.sumandas.shop.model.Variant;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductModule{

    @SerializedName("title") public String title;
    @SerializedName("products") public ArrayList<Product> products;
    @SerializedName("brandFilter") public ArrayList<BrandFilter> brandFilters;
    @SerializedName("sort") public SortOptions sortOptions;



}
