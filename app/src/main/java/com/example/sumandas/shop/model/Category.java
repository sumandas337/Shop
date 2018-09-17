package com.example.sumandas.shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category{

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("url") public String url;
    @SerializedName("image_url") public String imageUrl;
    @SerializedName("products_count")public int productCount;
    @SerializedName("sub_categories")public List<SubCategory> subCategories;


}
