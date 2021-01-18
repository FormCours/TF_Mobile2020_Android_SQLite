package be.technofuturtic.mobile.demodatabasefragment.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.technofuturtic.mobile.demodatabasefragment.db.DbHelper;
import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Dao;
import be.technofuturtic.mobile.demodatabasefragment.db.interfaces.Entity;

public abstract class DaoBase<TEntity extends Entity> implements Dao<TEntity> {

    protected Context context;
    protected DbHelper dbHelper;
    protected SQLiteDatabase db;

    private String tableName;
    private String columnId;


    public DaoBase(Context context, String tableName, String columnId) {
        this.context = context;
        this.tableName = tableName;
        this.columnId = columnId;
    }

    protected String getTableName() {
        return tableName;
    }

    protected String getColumnId() {
        return columnId;
    }


    //region Acces DB
    /**
     * Ouvrture de l'acces DB en lecture
     *
     * @return L'instance du RecipeDao
     */
    public Dao<TEntity> openReadable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    /**
     * Ouvrture de l'acces DB en ecriture
     *
     * @return L'instance du RecipeDao
     */
    public Dao<TEntity> openWritable() {
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
    //endregion


    //region CRUD
    protected abstract ContentValues entityToContentValues(TEntity entity);
    protected abstract TEntity cursorToEntity(Cursor c);

    @Override
    public long create(TEntity entity) {
        ContentValues cv = entityToContentValues(entity);

        return db.insert(getTableName(), null, cv);
    }

    @Override
    public TEntity get(long id) {
        //Cursor c = db.rawQuery("SELECT * FROM Recipe WHERE Id = ?", new String[] {String.valueOf(id)});

        Cursor c = db.query(getTableName(), null,
                getColumnId() + " = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (c.getCount() == 1) {
            c.moveToFirst();
            return cursorToEntity(c);
        }
        return null;
    }

    @Override
    public List<TEntity> getAll() {
        Cursor c = db.query(getTableName(), null, null, null, null, null, null);

        List<TEntity> recipes = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();

            while (c.isAfterLast()) {
                TEntity r = cursorToEntity(c);
                recipes.add(r);

                c.moveToNext();
            }
        }

        return recipes;
    }

    @Override
    public boolean update(TEntity entity) {
        ContentValues cv = entityToContentValues(entity);

        int nbRow = db.update(getTableName(), cv, getColumnId() + " = ? ",
                new String[]{String.valueOf(entity.getId())});

        return nbRow == 1;
    }

    @Override
    public boolean delete(long id) {
        int nbRow = db.delete(getTableName(), getColumnId() + " = ? ",new String[]{String.valueOf(id)});

        return nbRow == 1;
    }
    //endregion
}
