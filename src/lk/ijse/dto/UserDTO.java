package lk.ijse.dto;

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
public class UserDTO {
    private String userName;
    private String name;
    private String address;
    private LocalDate date;
    private String password;
}
