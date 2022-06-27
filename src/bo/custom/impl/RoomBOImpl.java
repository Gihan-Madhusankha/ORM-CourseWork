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
                    room.getRoomTypeId(), room.getType(), room.getKeyMoney(), room.getRoomQty()
            ));
        }
        return roomDTOList;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) {
        return roomDAO.save(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getRoomQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) {
        return roomDAO.update(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getRoomQty()));
    }

    @Override
    public boolean deleteRoom(String id) {
        return roomDAO.delete(id);
    }

    @Override
    public ArrayList<RoomDTO> getRoomDetailsByRoomTypeId(String roomTypeId) {
        ArrayList<Room> room = roomDAO.getRoom(roomTypeId);
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : room) {
            roomDTOS.add(new RoomDTO(
                    r.getRoomTypeId(), r.getType(), r.getKeyMoney(), r.getRoomQty()
            ));
        }
        return roomDTOS;
    }

}
