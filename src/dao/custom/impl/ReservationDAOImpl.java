package dao.custom.impl;

import dao.custom.ReservationDAO;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:40 PM
 **/

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getAll() {
        return null;
    }

    @Override
    public boolean save(Reservation entity) {
        return false;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public String generateId() {
        return null;
    }

    @Override
    public Reservation search(String s) {
        return null;
    }

}
