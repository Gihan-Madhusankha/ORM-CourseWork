package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
    private double keyMoney;
    private int roomQty;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList = new ArrayList<>();

}
