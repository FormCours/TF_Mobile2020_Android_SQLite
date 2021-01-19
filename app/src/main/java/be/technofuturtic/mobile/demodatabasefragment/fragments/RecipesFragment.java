package be.technofuturtic.mobile.demodatabasefragment.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import be.technofuturtic.mobile.demodatabasefragment.R;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

/**
 * A fragment representing a list of Items.
 */
public class RecipesFragment extends Fragment {

    private final static String ARG_RECIPES = "ARG_RECIPES";

    private ArrayList<Recipe> recipes;
    private RecipeAdapter recipeAdapter;

    public RecipesFragment() {
        recipes = new ArrayList<>();
    }

    public static RecipesFragment newInstance(ArrayList<Recipe> recipes) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_RECIPES, recipes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            recipes = getArguments().getParcelableArrayList(ARG_RECIPES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        recipeAdapter = new RecipeAdapter(recipes);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(recipeAdapter);
        }
        return view;
    }
}