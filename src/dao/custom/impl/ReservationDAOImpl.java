package dao.custom.impl;

import dao.custom.ReservationDAO;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

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
        Reservation r = new Reservation();
        r.setResId(entity.getResId());
        r.setDate(entity.getDate());
        r.setStatus(entity.getStatus());
        r.setStudent(entity.getStudent());
        r.setRoom(entity.getRoom());

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(r);
        transaction.commit();
        session.close();
        return true;
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
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Reservation ORDER BY resId DESC";
        List<Reservation> list = session.createQuery(hql).list();
        String st = null;
        for (Reservation r : list) {
            st = r.getResId();
            break;
        }
        transaction.commit();
        session.close();

        if (st != null) {
            int newID = Integer.parseInt(st.replace("Res.Id-", "")) + 1;
            return String.format("Res.Id-%03d", newID);

        } else {
            return "Res.Id-001";
        }

    }

    @Override
    public Reservation search(String s) {
        return null;
    }

}
