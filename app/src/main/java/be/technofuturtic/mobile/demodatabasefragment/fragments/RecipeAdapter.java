package be.technofuturtic.mobile.demodatabasefragment.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import be.technofuturtic.mobile.demodatabasefragment.R;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> items) {
        recipes = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        holder.tvTitle.setText(recipe.getName());
        holder.rbRating.setNumStars(recipe.getRating());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitle;
        public final RatingBar rbRating;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitle = (TextView) view.findViewById(R.id.tv_item_recipe_title);
            rbRating = (RatingBar) view.findViewById(R.id.rb_item_recipe_rating);
        }
    }
}