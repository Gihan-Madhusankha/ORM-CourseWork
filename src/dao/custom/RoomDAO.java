package dao.custom;

import dao.CrudDAO;
import entity.Room;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:09 PM
 **/

public interface RoomDAO extends CrudDAO<Room, String> {
    ArrayList<Room> getRoom(String roomTypeId) throws IOException;

    boolean updateQty(String value) throws IOException;

    void updateQTY(String roomTypeId) throws IOException;

//    boolean updateRoomQty(Room entity);
}
