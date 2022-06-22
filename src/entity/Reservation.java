package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author : Gihan Madhusankha
 * 2022-06-22 9:38 AM
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    private String resId;

    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Room room;
}
