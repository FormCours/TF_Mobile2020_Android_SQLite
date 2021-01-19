package be.technofuturtic.mobile.demodatabasefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import be.technofuturtic.mobile.demodatabasefragment.db.dao.IngredientDao;
import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeDao;
import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeIngredientDao;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeForm.NewRecipeModel;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeForm.RecipeFormFragment;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeList.RecipesFragment;
import be.technofuturtic.mobile.demodatabasefragment.models.Ingredient;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;
import be.technofuturtic.mobile.demodatabasefragment.models.RecipeIngredient;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, RecipeFormFragment.RecipeValidedListener {

    private Button btnNewRecipe;
    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;
    private RecipeIngredientDao recipeIngredientDao;
    private ArrayList<Recipe> recipes;

    public MainActivity() {
        recipeDao = new RecipeDao(this);
        ingredientDao = new IngredientDao(this);
        recipeIngredientDao = new RecipeIngredientDao(this);

        recipes = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewRecipe = findViewById(R.id.btn_main_new);
        btnNewRecipe.setOnClickListener(this);

        displayRecipes();
    }

    private void displayRecipes() {

        recipeDao.openReadable();
        recipes = (ArrayList<Recipe>) recipeDao.getAll();
        recipeDao.close();

        Fragment recipesFragment = RecipesFragment.newInstance(recipes);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_main_content, recipesFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_new:
                openAddRecipeFragment();
                break;
            default:
                throw new RuntimeException(String.format("Click not support on %s", v.toString()));
        }
    }

    private void openAddRecipeFragment() {
        RecipeFormFragment fragment = RecipeFormFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_main_content, fragment)
                .addToBackStack(null)
                .commit();

        btnNewRecipe.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRecipeValidedListener(NewRecipeModel newRecipe) {

        // Mapping vers les models DB
        Recipe recipe = new Recipe(newRecipe.getName(), newRecipe.getRating());

        // Ajouter de la recipe dans la DB
        recipeDao.openWritable();
        long recipeId = recipeDao.create(recipe);
        recipeDao.close();

        // Mise a jours de l'id de la recette
        recipe.setId(recipeId);

        // Ajouter dans la liste
        recipes.add(recipe);

        // Ajout des ingrédients
        for (NewRecipeModel.Ingredient ingredient : newRecipe.getIngredients()) {
            // Recup de l'id de l'ingrédient (ajout en db, si non existant)
            ingredientDao.openWritable();
            Long ingredientId = ingredientDao.getIdByName(ingredient.getName());
            if(ingredientId == null) {
                ingredientId = ingredientDao.create(new Ingredient(ingredient.getName(), ""));
            }
            ingredientDao.close();

            // Ajout du lien entre la recette et l'ingrédient
            RecipeIngredient link = new RecipeIngredient(
                    recipeId, ingredientId, ingredient.getQuantity()
            );
            recipeIngredientDao.openWritable();
            recipeIngredientDao.create(link);
            recipeIngredientDao.close();
        }

        // Ferme le fragement
        getSupportFragmentManager().popBackStack();
        btnNewRecipe.setVisibility(View.VISIBLE);
    }
}