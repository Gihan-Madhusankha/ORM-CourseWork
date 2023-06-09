package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:05 PM
 **/

public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws IOException {
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
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getRoomQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(dto.getRoomTypeId(), dto.getType(), dto.getKeyMoney(), dto.getRoomQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public ArrayList<RoomDTO> getRoomDetailsByRoomTypeId(String roomTypeId) throws IOException {
        ArrayList<Room> room = roomDAO.getRoom(roomTypeId);
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : room) {
            roomDTOS.add(new RoomDTO(
                    r.getRoomTypeId(), r.getType(), r.getKeyMoney(), r.getRoomQty()
            ));
        }
        return roomDTOS;
    }

    @Override
    public boolean updateRoomQty(String value) throws IOException {
        return roomDAO.updateQty(value);
    }

    @Override
    public void updateQtyOfRoom(String roomTypeId) throws IOException {
        roomDAO.updateQTY(roomTypeId);
    }

}
