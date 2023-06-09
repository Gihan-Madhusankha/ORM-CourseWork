package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author : Gihan Madhusankha
 * 2022-06-22 9:19 AM
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String password;
}
