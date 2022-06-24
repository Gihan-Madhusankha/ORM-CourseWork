package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Gihan Madhusankha
 * 2022-06-23 12:00 AM
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate date;
    private String gender;
}
