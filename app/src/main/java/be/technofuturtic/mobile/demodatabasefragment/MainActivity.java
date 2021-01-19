package be.technofuturtic.mobile.demodatabasefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeDao;
import be.technofuturtic.mobile.demodatabasefragment.fragments.RecipesFragment;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    Button btnNewRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewRecipe = findViewById(R.id.btn_main_new);
        btnNewRecipe.setOnClickListener(this);

        displayRecipes();
    }

    private void displayRecipes() {

        RecipeDao recipeDao = new RecipeDao(getApplicationContext());
        recipeDao.openReadable();
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeDao.getAll();
        recipeDao.close();

        //FIXME Delete this !
        recipes.add(new Recipe(13, "Hello", 3));
        recipes.add(new Recipe(42, "test", 5));
        recipes.add(new Recipe(52, "Bye", 1));

        Fragment recipesFragment = RecipesFragment.newInstance(recipes);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_main_content, recipesFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_new:
                // TODO Faire l'ajout
                break;
            default:
                throw new RuntimeException(String.format("Click not support on %s", v.toString()));
        }
    }
}