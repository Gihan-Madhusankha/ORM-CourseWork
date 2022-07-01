package dao.custom.impl;

import dao.custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:54 PM
 **/

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws IOException {
        return null;
    }

    @Override
    public boolean save(User entity) throws IOException {
        User user = new User();
        user.setUserName(entity.getUserName());
        user.setName(entity.getName());
        user.setAddress(entity.getAddress());
        user.setDate(entity.getDate());
        user.setPassword(entity.getPassword());

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public String generateId() throws IOException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT userName FROM User ORDER BY userName DESC";
        List<String> list = session.createQuery(hql).list();
        String id = null;
        for (String s : list) {
            id = s;
            break;
        }
        transaction.commit();
        session.close();

        if (id != null) {
            int newId = Integer.parseInt(id.replace("USER-", "")) + 1;
            return String.format("USER-%03d", newId);

        } else {
            return "USER-001";
        }

    }

    @Override
    public User search(String s) {
        return null;
    }

    @Override
    public String getPassword(String text) throws IOException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User";
        String pwd = null;
        List<User> list = session.createQuery(hql).list();
        for (User user : list) {
            if (user.getUserName().equals(text)) {
                pwd = user.getPassword();
            }
        }

        transaction.commit();
        session.close();
        return pwd;
    }
}
