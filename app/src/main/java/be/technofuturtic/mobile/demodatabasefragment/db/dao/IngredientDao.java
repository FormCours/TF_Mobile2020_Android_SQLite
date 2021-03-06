package be.technofuturtic.mobile.demodatabasefragment.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import be.technofuturtic.mobile.demodatabasefragment.db.DbQuery;
import be.technofuturtic.mobile.demodatabasefragment.models.Ingredient;

public class IngredientDao extends DaoBase<Ingredient> {

    public IngredientDao(Context context) {
        super(context, DbQuery.Ingredient.TABLE_NAME, DbQuery.Ingredient.COL_ID);
    }


    public Long getIdByName(String name) {
        Cursor c = db.query(getTableName(), null,
                DbQuery.Ingredient.COL_NAME + " = ?", new String[]{name},
                null, null, null);

        if (c.getCount() == 1) {
            c.moveToFirst();
            return cursorToEntity(c).getId();
        }
        return null;
    }


    @Override
    protected ContentValues entityToContentValues(Ingredient entity) {
        ContentValues cv = new ContentValues();
        cv.put(DbQuery.Ingredient.COL_NAME, entity.getName());
        cv.put(DbQuery.Ingredient.COL_DESC, entity.getDesc());
        return cv;
    }

    @Override
    protected Ingredient cursorToEntity(Cursor c) {
        return new Ingredient(
            c.getLong(c.getColumnIndex(DbQuery.Ingredient.COL_ID)),
            c.getString(c.getColumnIndex(DbQuery.Ingredient.COL_NAME)),
            c.getString(c.getColumnIndex(DbQuery.Ingredient.COL_DESC))
        );
    }
}
