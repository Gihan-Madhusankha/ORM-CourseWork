package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:53 PM
 **/

public interface UserDAO extends CrudDAO<User, String> {
    String getPassword(String text) throws IOException;

    ArrayList<User> getDetails(String user_name) throws IOException;
}
