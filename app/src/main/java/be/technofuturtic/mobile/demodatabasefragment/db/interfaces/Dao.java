package be.technofuturtic.mobile.demodatabasefragment.db.interfaces;

import java.util.List;

public interface Dao<TEntity extends Entity> {

    // Create
    long create(TEntity entity);

    // Read
    TEntity get(long id);
    List<TEntity> getAll();

    // Update
    boolean update(TEntity entity);

    // Delete
    boolean delete (long id);

}
