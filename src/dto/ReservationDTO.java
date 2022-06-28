package dto;

import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Gihan Madhusankha
 * 2022-06-23 12:07 AM
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private String status;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
}
