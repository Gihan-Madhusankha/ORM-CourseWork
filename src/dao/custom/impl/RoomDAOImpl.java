package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
                    room.getRoomTypeId(), room.getType(), room.getKeyMoney(), room.getRoomQty()
            ));
        }
        transaction.commit();
        session.close();
        return roomList;

    }

    @Override
    public boolean save(Room entity) {
        Room room = new Room();
        room.setRoomTypeId(entity.getRoomTypeId());
        room.setType(entity.getType());
        room.setKeyMoney(entity.getKeyMoney());
        room.setRoomQty(entity.getRoomQty());

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Room SET type = :room_type, keyMoney = :room_keyMoney, roomQty = :room_qty WHERE roomTypeId = :room_typeId";
        Query query = session.createQuery(hql);
        query.setParameter("room_type", entity.getType());
        query.setParameter("room_keyMoney", entity.getKeyMoney());
        query.setParameter("room_qty", entity.getRoomQty());
        query.setParameter("room_typeId", entity.getRoomTypeId());

        boolean b = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return b;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Room del = session.load(Room.class, s);
        session.delete(del);
        transaction.commit();
        session.close();
        return true;
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
