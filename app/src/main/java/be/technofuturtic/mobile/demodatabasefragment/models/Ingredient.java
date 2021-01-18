package be.technofuturtic.mobile.demodatabasefragment.models;

import android.os.Parcel;
import android.os.Parcelable;

import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Entity;

public class Ingredient implements Entity, Parcelable {

    private long ingredientId;
    private String name;
    private String desc;

    public Ingredient(long ingredientId, String name, String desc) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.desc = desc;
    }

    public Ingredient(String name, String desc) {
        this(0, name, desc);
    }

    protected Ingredient(Parcel in) {
        ingredientId = in.readLong();
        name = in.readString();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ingredientId);
        dest.writeString(name);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public long getId() {
        return ingredientId;
    }

    public void setId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
