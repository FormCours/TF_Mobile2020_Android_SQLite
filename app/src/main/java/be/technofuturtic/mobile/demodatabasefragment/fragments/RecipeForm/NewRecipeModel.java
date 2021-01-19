package be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeForm;

import java.util.ArrayList;

public class NewRecipeModel {

    public static class Ingredient {
        private String name;
        private String quantity;

        public Ingredient(String name, String quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return this.name + " " + this.quantity;
        }
    }

    private String name;
    private Integer Rating;
    private ArrayList<Ingredient> ingredients;

    public NewRecipeModel() {
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(0, ingredient);
    }
}
