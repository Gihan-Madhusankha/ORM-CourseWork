package bo.custom;

import dto.RoomDTO;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:04 PM
 **/

public interface RoomBO {
    ArrayList<RoomDTO> getAllRooms();

    boolean saveRoom(RoomDTO dto);

    boolean updateRoom(RoomDTO dto);

    boolean deleteRoom(String id);

    ArrayList<RoomDTO> getRoomDetailsByRoomTypeId(String roomTypeId);
}
