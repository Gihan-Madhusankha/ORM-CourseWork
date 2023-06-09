package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import lk.ijse.util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:10 PM
 **/

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws IOException {
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
    public boolean save(Room entity) throws IOException {
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
    public boolean update(Room entity) throws IOException {
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
    public boolean delete(String s) throws IOException {
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

    @Override
    public ArrayList<Room> getRoom(String roomTypeId) throws IOException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Room WHERE roomTypeId = :room_type_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_type_id", roomTypeId);
        List<Room> list = query.list();
        ArrayList<Room> arrayList = new ArrayList<>();
        arrayList.add(new Room(list.get(0).getRoomTypeId(), list.get(0).getType(), list.get(0).getKeyMoney(), list.get(0).getRoomQty()));

        transaction.commit();
        session.close();
        return arrayList;
    }

    @Override
    public boolean updateQty(String value) throws IOException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, value);
        String hql = "UPDATE Room SET roomQty = :room_qty WHERE roomTypeId = :room_type_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_qty", room.getRoomQty() - 1);
        query.setParameter("room_type_id", value);
        boolean b = query.executeUpdate() > 0;

        transaction.commit();
        session.close();
        return b;
    }

    @Override
    public void updateQTY(String roomTypeId) throws IOException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, roomTypeId);
        String hql = "UPDATE Room SET roomQty = :room_qty WHERE roomTypeId = :room_type_id";
        Query query = session.createQuery(hql);
        query.setParameter("room_qty", room.getRoomQty() + 1);
        query.setParameter("room_type_id", roomTypeId);
        boolean b = query.executeUpdate() > 0;

        transaction.commit();
        session.close();
    }

}
