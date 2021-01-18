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

public class RecipeDao implements Dao<Recipe> {

    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Permet la création du Dao RecipeDao
     *
     * @param context Le context de l'app
     */
    public RecipeDao(Context context) {
        this.context = context;
    }

    /**
     * Ouvrture de l'acces DB en lecture
     *
     * @return L'instance du RecipeDao
     */
    public RecipeDao openReadable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    /**
     * Ouvrture de l'acces DB en ecriture
     *
     * @return L'instance du RecipeDao
     */
    public RecipeDao openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Fermeture de l'acces DB
     */
    public void close() {
        db.close();
        dbHelper.close();
    }


    @Override
    public long create(Recipe entity) {
        ContentValues cv = recipeToContentValues(entity);

        return db.insert(DbQuery.Recipe.TABLE_NAME, null, cv);
    }

    private ContentValues recipeToContentValues(Recipe entity) {
        ContentValues cv = new ContentValues();
        cv.put(DbQuery.Recipe.COL_NAME, entity.getName());
        cv.put(DbQuery.Recipe.COL_RATING, entity.getRating());
        return cv;
    }

    @Override
    public Recipe get(long id) {
        //Cursor c = db.rawQuery("SELECT * FROM Recipe WHERE Id = ?", new String[] {String.valueOf(id)});

        Cursor c = db.query(DbQuery.Recipe.TABLE_NAME, null,
                DbQuery.Recipe.COL_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (c.getCount() == 1) {
            c.moveToFirst();
            return cursorToRecipe(c);
        }
        return null;
    }

    @Override
    public List<Recipe> getAll() {
        Cursor c = db.query(DbQuery.Recipe.TABLE_NAME, null, null, null, null, null, null);

        List<Recipe> recipes = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();

            while (c.isAfterLast()) {
                Recipe r = cursorToRecipe(c);
                recipes.add(r);

                c.moveToNext();
            }
        }

        return recipes;
    }

    private Recipe cursorToRecipe(Cursor c) {
        long id2 = c.getLong(c.getColumnIndex(DbQuery.Recipe.COL_ID));
        String name = c.getString(c.getColumnIndex(DbQuery.Recipe.COL_NAME));

        Integer rating = null;
        if (!c.isNull(c.getColumnIndex(DbQuery.Recipe.COL_RATING))) {
            rating = c.getInt(c.getColumnIndex(DbQuery.Recipe.COL_RATING));
        }

        return new Recipe(id2, name, rating);
    }

    @Override
    public boolean update(Recipe entity) {
        ContentValues cv = recipeToContentValues(entity);

        int nbRow = db.update(DbQuery.Recipe.TABLE_NAME, cv, DbQuery.Recipe.COL_ID + " = ? ",
                new String[]{String.valueOf(entity.getId())});

        return nbRow == 1;
    }

    @Override
    public boolean delete(long id) {
        int nbRow = db.delete(DbQuery.Recipe.TABLE_NAME, DbQuery.Recipe.COL_ID + " = ? ",new String[]{String.valueOf(id)});

        return nbRow == 1;
    }
}
