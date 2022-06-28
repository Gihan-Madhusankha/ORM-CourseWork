package view.tm;

import dto.RoomDTO;
import dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;

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
