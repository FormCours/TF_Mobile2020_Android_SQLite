package be.technofuturtic.mobile.demodatabasefragment.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import be.technofuturtic.mobile.demodatabasefragment.db.DbQuery;
import be.technofuturtic.mobile.demodatabasefragment.models.RecipeIngredient;

public class RecipeIngredientDao extends DaoBase<RecipeIngredient> {

    public RecipeIngredientDao(Context context) {
        super(context, DbQuery.RecipeIngredient.TABLE_NAME, DbQuery.RecipeIngredient.COL_ID);
    }

    @Override
    protected ContentValues entityToContentValues(RecipeIngredient entity) {
        ContentValues cv = new ContentValues();
        cv.put(DbQuery.RecipeIngredient.COL_ID, entity.getId());
        cv.put(DbQuery.RecipeIngredient.COL_RECIPE_ID, entity.getRecipeId());
        cv.put(DbQuery.RecipeIngredient.COL_INGREDIENT_ID, entity.getIngredientId());
        cv.put(DbQuery.RecipeIngredient.COL_QUANTITY, entity.getQuantity());

        return cv;
    }

    @Override
    protected RecipeIngredient cursorToEntity(Cursor c) {
        return new RecipeIngredient(
                c.getLong(c.getColumnIndex(DbQuery.RecipeIngredient.COL_ID)),
                c.getLong(c.getColumnIndex(DbQuery.RecipeIngredient.COL_RECIPE_ID)),
                c.getLong(c.getColumnIndex(DbQuery.RecipeIngredient.COL_INGREDIENT_ID)),
                c.getString(c.getColumnIndex(DbQuery.RecipeIngredient.COL_QUANTITY))
        );
    }
}
