package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;

    @ToString.Exclude
    @ManyToOne
    private Student student;

    @ToString.Exclude
    @ManyToOne
    private Room room;

    public Reservation(String resId, LocalDate date, String status) {
    }

//    public Reservation(String resId, LocalDate date, String status) {
//        this.resId = resId;
//        this.date = date;
//        this.status = status;
//    }
}
