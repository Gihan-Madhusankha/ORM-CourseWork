package entity;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-22 9:32 AM
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "student_id")
    private String id;

    private String name;
    private String address;
    private String contactNo;

    @Column(name = "regDate")
    private LocalDate date;

    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Reservation> reservationList = new ArrayList<>();

}
