package lk.ijse.util;

import lk.ijse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Gihan Madhusankha
 * 2022-06-21 4:45 PM
 **/

public class SQLUtil {
    private static PreparedStatement execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            statement.setObject((i + 1), params[i]);
        }
        return statement;
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeQuery();
    }

    public static boolean executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeUpdate() > 0;
    }

    /*public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate() > 0);
        }
    }
*/
}
