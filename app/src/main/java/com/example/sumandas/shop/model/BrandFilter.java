package com.example.sumandas.shop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BrandFilter implements Parcelable {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("url") public String url;
    @SerializedName("slug") public String slug;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.slug);
    }

    public BrandFilter() {
    }

    protected BrandFilter(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.slug = in.readString();
    }

    public static final Parcelable.Creator<BrandFilter> CREATOR = new Parcelable.Creator<BrandFilter>() {
        @Override
        public BrandFilter createFromParcel(Parcel source) {
            return new BrandFilter(source);
        }

        @Override
        public BrandFilter[] newArray(int size) {
            return new BrandFilter[size];
        }
    };
}
