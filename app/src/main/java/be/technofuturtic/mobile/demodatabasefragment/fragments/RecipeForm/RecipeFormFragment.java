package be.technofuturtic.mobile.demodatabasefragment.fragments.RecipeForm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import be.technofuturtic.mobile.demodatabasefragment.R;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFormFragment extends Fragment implements View.OnClickListener {

    //region Callback
    @FunctionalInterface
    public  interface RecipeValidedListener {
        void onRecipeValidedListener(NewRecipeModel recipe);
    }

    private RecipeValidedListener validedListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RecipeValidedListener) {
            validedListener = (RecipeValidedListener)context;
        }
        else {
            throw new RuntimeException("Il est necessaire d'implémenter RecipeValidedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        validedListener= null;
    }
    //endregion


    EditText etRecipeName, etRecipeRating;
    Button btnAdd;

    AutoCompleteTextView etRecipeIngredientName;
    EditText etRecipeIngredientQuantity;
    Button btnAddIngredient;

    ListView lvRecipeIngredients;
    ArrayAdapter<NewRecipeModel.Ingredient> ingredientsAdapter;

    NewRecipeModel newRecipeModel;


    public RecipeFormFragment() {
        // Required empty public constructor
    }

    public static RecipeFormFragment newInstance() {
        RecipeFormFragment fragment = new RecipeFormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newRecipeModel = new NewRecipeModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_form, container, false);

        etRecipeName = view.findViewById(R.id.et_frag_form_recipe_name);
        etRecipeRating = view.findViewById(R.id.et_frag_form_recipe_rating);

        btnAdd = view.findViewById(R.id.btn_frag_form_recipe_valided);
        btnAdd.setOnClickListener(this);

        etRecipeIngredientName = view.findViewById(R.id.et_frag_form_recipe_ingredient_name);
        etRecipeIngredientQuantity = view.findViewById(R.id.et_frag_form_recipe_ingredient_quantity);

        btnAddIngredient = view.findViewById(R.id.btn_frag_form_recipe_ingredient_add);
        btnAddIngredient.setOnClickListener(this);

        lvRecipeIngredients = view.findViewById(R.id.lv_frag_from_recipe_ingredients);
        ingredientsAdapter = new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                newRecipeModel.getIngredients()
        );
        lvRecipeIngredients.setAdapter(ingredientsAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_frag_form_recipe_valided:
                validateForm();
                break;
            case R.id.btn_frag_form_recipe_ingredient_add:
                addIngredient();
                break;
        }
    }

    private void addIngredient() {
        newRecipeModel.addIngredient(new NewRecipeModel.Ingredient(
            etRecipeIngredientName.getText().toString(),
            etRecipeIngredientQuantity.getText().toString()
        ));
        ingredientsAdapter.notifyDataSetChanged();

        etRecipeIngredientName.setText("");
        etRecipeIngredientQuantity.setText("");

        etRecipeIngredientName.requestFocus();
    }

    private void validateForm() {
        String recipeName = etRecipeName.getText().toString();

        String ratingValue = etRecipeRating.getText().toString();
        Integer recipeRating = ratingValue.trim().equals("") ? null : Integer.parseInt(ratingValue);

        newRecipeModel.setName(recipeName);
        newRecipeModel.setRating(recipeRating);

        this.validedListener.onRecipeValidedListener(newRecipeModel);
    }
}