package be.technofuturtic.mobile.demodatabasefragment.models;

import android.os.Parcel;
import android.os.Parcelable;

import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Entity;

public class RecipeIngredient implements Entity, Parcelable {

    private long recipeIngredientId;
    private long recipeId;
    private long ingredientId;
    private String quantity;

    public RecipeIngredient(long recipeIngredientId, long recipeId, long ingredientId, String quantity) {
        this.recipeIngredientId = recipeIngredientId;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public RecipeIngredient(long recipeId, long ingredientId, String quantity) {
        this(0, recipeId, ingredientId, quantity);
    }

    protected RecipeIngredient(Parcel in) {
        recipeId = in.readLong();
        ingredientId = in.readLong();
        quantity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(recipeId);
        dest.writeLong(ingredientId);
        dest.writeString(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
        @Override
        public RecipeIngredient createFromParcel(Parcel in) {
            return new RecipeIngredient(in);
        }

        @Override
        public RecipeIngredient[] newArray(int size) {
            return new RecipeIngredient[size];
        }
    };

    public long getId() {
        return recipeIngredientId;
    }

    public void setId(long recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
