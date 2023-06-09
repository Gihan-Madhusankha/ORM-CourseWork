package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.view.tm.ReservationListTM;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public class ReservationBOImpl implements ReservationBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public String generateResId() throws IOException {
        return reservationDAO.generateId();
    }

    @Override
    public boolean bookTheRoom(ReservationDTO dto) throws IOException {
        Student student = new Student(dto.getStudentDTO().getId(), dto.getStudentDTO().getName(), dto.getStudentDTO().getAddress(), dto.getStudentDTO().getContactNo(),
                dto.getStudentDTO().getDob(), dto.getStudentDTO().getGender());
        Room room = new Room(dto.getRoomDTO().getRoomTypeId(), dto.getRoomDTO().getType(), dto.getRoomDTO().getKeyMoney(), dto.getRoomDTO().getRoomQty());

        return reservationDAO.save(new Reservation(
                dto.getResId(), dto.getDate(), dto.getStatus(), student, room
        ));
    }

    @Override
    public ArrayList<ReservationListTM> getAllBookingRoomDetails() throws IOException {
        ArrayList<Reservation> all = reservationDAO.getAll();
        ArrayList<ReservationListTM> dtos = new ArrayList<>();
        for (Reservation res : all) {
            dtos.add(new ReservationListTM(
                    res.getResId(), res.getDate(), res.getStatus(), res.getStudent().getId(), res.getRoom().getRoomTypeId()
            ));
        }
        return dtos;
    }

    @Override
    public boolean deleteReservationByResID(String resId) throws IOException {
        return reservationDAO.delete(resId);
    }


}
