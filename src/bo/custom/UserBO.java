package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

import java.io.IOException;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:52 PM
 **/

public interface UserBO extends SuperBO {
    String generateUserId() throws IOException;

    boolean saveUser(UserDTO dto) throws IOException;
}
