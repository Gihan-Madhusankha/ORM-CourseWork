package bo.custom.impl;

import bo.custom.ReservationBO;
import dao.custom.QueryDAO;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.ReservationDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import dto.ReservationDTO;
import entity.Reservation;
import entity.Room;
import entity.Student;
import view.tm.ReservationListTM;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public class ReservationBOImpl implements ReservationBO {
    QueryDAO queryDAO = new QueryDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public String generateResId() {
        return reservationDAO.generateId();
    }

    @Override
    public boolean bookTheRoom(ReservationDTO dto) {
        Student student = new Student(dto.getStudentDTO().getId(), dto.getStudentDTO().getName(), dto.getStudentDTO().getAddress(), dto.getStudentDTO().getContactNo(),
                dto.getStudentDTO().getDob(), dto.getStudentDTO().getGender());
        Room room = new Room(dto.getRoomDTO().getRoomTypeId(), dto.getRoomDTO().getType(), dto.getRoomDTO().getKeyMoney(), dto.getRoomDTO().getRoomQty());

        return reservationDAO.save(new Reservation(
                dto.getResId(), dto.getDate(), dto.getStatus(), student, room
        ));
    }

    @Override
    public ArrayList<ReservationListTM> getAllBookingRoomDetails() {
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
    public boolean deleteReservationByResID(String resId) {
        return reservationDAO.delete(resId);
    }
}
