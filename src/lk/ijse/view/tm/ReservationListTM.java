package lk.ijse.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Gihan Madhusankha
 * 2022-06-29 11:48 AM
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationListTM {
    private String resId;
    private LocalDate date;
    private String status;
    private String student;
    private String room;

}
