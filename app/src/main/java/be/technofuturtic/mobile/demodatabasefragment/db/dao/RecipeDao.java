package be.technofuturtic.mobile.demodatabasefragment.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.technofuturtic.mobile.demodatabasefragment.db.DbHelper;
import be.technofuturtic.mobile.demodatabasefragment.db.DbQuery;
import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Dao;
import be.technofuturtic.mobile.demodatabasefragment.models.Recipe;

public class RecipeDao extends DaoBase<Recipe> {

    public RecipeDao(Context context) {
        super(context, DbQuery.Recipe.TABLE_NAME, DbQuery.Recipe.COL_ID);
    }


    @Override
    protected ContentValues entityToContentValues(Recipe entity) {
        ContentValues cv = new ContentValues();
        cv.put(DbQuery.Recipe.COL_NAME, entity.getName());
        cv.put(DbQuery.Recipe.COL_RATING, entity.getRating());
        return cv;
    }

    @Override
    protected Recipe cursorToEntity(Cursor c) {

        long id2 = c.getLong(c.getColumnIndex(DbQuery.Recipe.COL_ID));
        String name = c.getString(c.getColumnIndex(DbQuery.Recipe.COL_NAME));

        Integer rating = null;
        if (!c.isNull(c.getColumnIndex(DbQuery.Recipe.COL_RATING))) {
            rating = c.getInt(c.getColumnIndex(DbQuery.Recipe.COL_RATING));
        }

        return new Recipe(id2, name, rating);
    }
}
