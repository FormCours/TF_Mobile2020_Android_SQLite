package be.technofuturtic.mobile.demodatabasefragment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static be.technofuturtic.mobile.demodatabasefragment.db.DbQuery.DB_NAME;
import static be.technofuturtic.mobile.demodatabasefragment.db.DbQuery.DB_VERSION;


public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se lance si l'application ne possede pas la base de données
        db.execSQL(DbQuery.Recipe.QUERY_CREATE);
        db.execSQL(DbQuery.Ingredient.QUERY_CREATE);
        db.execSQL(DbQuery.RecipeIngredient.QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se lance si une database existe et que sa version est inférieur a la version actuel
        db.execSQL(DbQuery.Recipe.QUERY_DROP);
        db.execSQL(DbQuery.Ingredient.QUERY_DROP);
        db.execSQL(DbQuery.RecipeIngredient.QUERY_DROP);

        onCreate(db);
    }
}
