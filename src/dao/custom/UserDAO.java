package dao.custom;

import dao.CrudDAO;
import entity.User;

import java.io.IOException;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:53 PM
 **/

public interface UserDAO extends CrudDAO<User, String> {
    String getPassword(String text) throws IOException;
}
