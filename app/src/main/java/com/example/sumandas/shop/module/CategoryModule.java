package com.example.sumandas.shop.module;

import com.example.sumandas.shop.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryModule{

    @SerializedName("title") public String title;
    @SerializedName("categories") public ArrayList<Category> categories;

}
