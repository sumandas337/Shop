package com.example.sumandas.shop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("full_name") public String fullName;
    @SerializedName("images") public List<String> images;
    @SerializedName("brand") public Brand brand;
    @SerializedName("defaultVariant") public Variant variant;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeStringList(this.images);
        dest.writeParcelable(this.brand, flags);
        dest.writeParcelable(this.variant, flags);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.fullName = in.readString();
        this.images = in.createStringArrayList();
        this.brand = in.readParcelable(Brand.class.getClassLoader());
        this.variant = in.readParcelable(Variant.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
