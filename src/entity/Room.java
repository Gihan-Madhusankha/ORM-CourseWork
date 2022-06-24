package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double keyMoney;

    @Column(nullable = false)
    private int roomQty;

    @ToString.Exclude
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public Room(String roomId, String type, double keyMoney, int roomQty) {
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.roomQty = roomQty;
    }
}
