package be.technofuturtic.mobile.demodatabasefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeDao;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeDao recipeDao = new RecipeDao(this);

        /*
        // Ajout d'un element en DB
        Recipe recipe = new Recipe("Demo");

        recipeDao.openWritable();
        recipeDao.create(recipe);
        recipeDao.close();
        */

        /*
        // Obtenir un element via son ID
        recipeDao.openReadable();
        Recipe recup = recipeDao.get(1);
        recipeDao.close();

        Toast.makeText(this, "Recette : " + recup.getName(), Toast.LENGTH_LONG).show();
         */

    }
}