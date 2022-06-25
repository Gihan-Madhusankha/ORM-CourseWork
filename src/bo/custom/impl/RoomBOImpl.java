package bo.custom.impl;

import bo.custom.RoomBO;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:05 PM
 **/

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public ArrayList<RoomDTO> getAllRooms() {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : all) {
            roomDTOList.add(new RoomDTO(
                    room.getRoomId(), room.getType(), room.getKeyMoney(), room.getRoomQty()
            ));
        }
        return roomDTOList;
    }
}