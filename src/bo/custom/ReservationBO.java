package bo.custom;

import dto.ReservationDTO;
import view.tm.ReservationListTM;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public interface ReservationBO {
    String generateResId();

    boolean bookTheRoom(ReservationDTO dto);

    ArrayList<ReservationListTM> getAllBookingRoomDetails();

    boolean deleteReservationByResID(String resId);
}
