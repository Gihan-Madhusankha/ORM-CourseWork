package lk.ijse.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Gihan Madhusankha
 * 2022-06-28 5:04 PM
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationList {
    private String stId;
    private String name;
    private String roomTypeId;
    private String type;
    private String status;
}
