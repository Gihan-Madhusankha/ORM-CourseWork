package lk.ijse.dao;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-22 10:41 AM
 **/

public interface CrudDAO<T, ID> extends SuperDAO {
    ArrayList<T> getAll() throws IOException;

    boolean save(T entity) throws IOException;

    boolean update(T entity) throws IOException;

    boolean delete(ID id) throws IOException;

    boolean exists(ID id);

    String generateId() throws IOException;

    T search(ID id);
}
