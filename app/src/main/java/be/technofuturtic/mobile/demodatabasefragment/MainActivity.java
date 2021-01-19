package be.technofuturtic.mobile.demodatabasefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.technofuturtic.mobile.demodatabasefragment.db.dao.RecipeDao;
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