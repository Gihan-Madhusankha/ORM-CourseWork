package dao;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-22 10:41 AM
 **/

public interface CrudDAO<T, ID> {
    ArrayList<T> getAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);

    boolean exists(ID id);

    String generateId();

    T search(ID id);
}
