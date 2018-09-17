package com.example.sumandas.shop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Brand implements Parcelable {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("url") public String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public Brand() {
    }

    protected Brand(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };
}
