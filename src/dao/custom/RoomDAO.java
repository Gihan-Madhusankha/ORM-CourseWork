package dao.custom;

import dao.CrudDAO;
import entity.Room;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:09 PM
 **/

public interface RoomDAO extends CrudDAO<Room, String> {
    ArrayList<Room> getRoom(String roomTypeId);

    boolean updateQty(String value);

    void updateQTY(String roomTypeId);

//    boolean updateRoomQty(Room entity);
}
