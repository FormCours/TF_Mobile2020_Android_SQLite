package be.technofuturtic.mobile.demodatabasefragment.models;

import android.os.Parcel;
import android.os.Parcelable;

import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Entity;

public class Recipe implements Entity, Parcelable {

    //region Field
    private long recipeId;
    private String name;
    private Integer rating;
    //endregion

    //region Constructor
    public Recipe(long recipeId, String name, Integer rating) {
        this.recipeId = recipeId;
        this.name = name;
        this.rating = rating;
    }

    public Recipe(String name) {
        this(0, name, null);
    }
    //endregion

    //region Getters / Setters
    @Override
    public long getId() {
        return recipeId;
    }

    public void setId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    //endregion


    //region Parcelable
    protected Recipe(Parcel in) {
        recipeId = in.readLong();
        name = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readInt();
        }

        // Alternative possible pour le Integer
        // rating = (Integer) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(recipeId);
        dest.writeString(name);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rating);
        }

        // Alternative possible pour le Integer
        // dest.writeSerializable(this.rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    //endregion
}
