package be.technofuturtic.mobile.demodatabasefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeDao;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeForm.RecipeFormFragment;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeList.RecipesFragment;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, RecipeFormFragment.RecipeValidedListener {

    private Button btnNewRecipe;
    private RecipeDao recipeDao;
    private ArrayList<Recipe> recipes;

    public MainActivity() {
        recipeDao = new RecipeDao(this);
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
    public void onRecipeValidedListener(Recipe recipe) {

        // Ajouter dans la DB
        recipeDao.openWritable();
        recipeDao.create(recipe);
        recipeDao.close();

        // Ajouter dans la liste
        recipes.add(recipe);

        // Ferme le fragement
        getSupportFragmentManager().popBackStack();
        btnNewRecipe.setVisibility(View.VISIBLE);
    }
}