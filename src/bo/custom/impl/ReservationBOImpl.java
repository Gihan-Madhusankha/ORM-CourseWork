package bo.custom.impl;

import bo.BOFactory;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;
import view.tm.ReservationListTM;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public class ReservationBOImpl implements ReservationBO {
    QueryDAO queryDAO = (QueryDAO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.QUERY);
    ReservationDAO reservationDAO = (ReservationDAO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.RESERVATION);
    RoomDAO roomDAO = (RoomDAO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ROOM);

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
