package com.example.sumandas.shop.module;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class Module {

    @SerializedName("data") private JsonElement data;
    @SerializedName("name") private String name;

    public JsonElement getData() {
        return data;
    }

    public String getName() {
        return name;
    }

}
