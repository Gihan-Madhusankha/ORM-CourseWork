package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.ReservationDTO;
import view.tm.ReservationListTM;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public interface ReservationBO extends SuperBO {
    String generateResId() throws IOException;

    boolean bookTheRoom(ReservationDTO dto) throws IOException;

    ArrayList<ReservationListTM> getAllBookingRoomDetails() throws IOException;

    boolean deleteReservationByResID(String resId) throws IOException;

}
