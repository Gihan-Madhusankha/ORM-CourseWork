package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.RoomDTO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:04 PM
 **/

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRooms() throws IOException;

    boolean saveRoom(RoomDTO dto) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;

    ArrayList<RoomDTO> getRoomDetailsByRoomTypeId(String dto) throws IOException;

    boolean updateRoomQty(String value) throws IOException;

    void updateQtyOfRoom(String roomTypeId) throws IOException;

//    boolean updateRoomQty(RoomDTO dto);
}
