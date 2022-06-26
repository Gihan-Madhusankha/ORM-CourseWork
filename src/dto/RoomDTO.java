package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Gihan Madhusankha
 * 2022-06-23 12:06 AM
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int roomQty;


}
