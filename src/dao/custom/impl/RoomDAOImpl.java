package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:10 PM
 **/

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Room";
        List<Room> list = session.createQuery(hql).list();
        ArrayList<Room> roomList = new ArrayList<>();
        for (Room room : list) {
            roomList.add(new Room(
                    room.getRoomId(), room.getType(), room.getKeyMoney(), room.getRoomQty()
            ));
        }
        transaction.commit();
        session.close();
        return roomList;

    }

    @Override
    public boolean save(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
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
    public Room search(String s) {
        return null;
    }
}
