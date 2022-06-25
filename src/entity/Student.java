package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(name = "student_id", nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactNo;

    @Column(name = "regDate", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String gender;

    @ToString.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public Student(String id, String name, String address, String contactNo, LocalDate date, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.date = date;
        this.gender = gender;
    }
}
