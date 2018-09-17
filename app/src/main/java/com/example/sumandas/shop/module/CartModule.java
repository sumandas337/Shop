package com.example.sumandas.shop.module;

import com.google.gson.annotations.SerializedName;

public class CartModule {

    @SerializedName("currency") public String currency;
    @SerializedName("youPay") public int youPay;
    @SerializedName("savings") public int savings;
    @SerializedName("zoppie_offer") public String offer;

}

