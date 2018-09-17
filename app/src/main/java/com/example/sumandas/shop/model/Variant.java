package com.example.sumandas.shop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Variant implements Parcelable {
    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("full_name") public String fullName;
    @SerializedName("images") public List<String> images;
    @SerializedName("mrp") public float mrp;
    @SerializedName("discount") public float discount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.discount);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeStringList(this.images);
        dest.writeFloat(this.mrp);
        dest.writeString(this.id);
    }

    public Variant() {
    }

    protected Variant(Parcel in) {
        this.discount = in.readFloat();
        this.name = in.readString();
        this.fullName = in.readString();
        this.images = in.createStringArrayList();
        this.mrp = in.readFloat();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<Variant> CREATOR = new Parcelable.Creator<Variant>() {
        @Override
        public Variant createFromParcel(Parcel source) {
            return new Variant(source);
        }

        @Override
        public Variant[] newArray(int size) {
            return new Variant[size];
        }
    };
}
