package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Gihan Madhusankha
 * 2022-06-18 11:01 PM
 **/

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    private String roomId;
    private String type;

    @Column(name = "monthly_rental")
    private double rental;
    private int qtyOnHand;

}
