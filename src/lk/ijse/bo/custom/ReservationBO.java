package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.view.tm.ReservationListTM;

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
