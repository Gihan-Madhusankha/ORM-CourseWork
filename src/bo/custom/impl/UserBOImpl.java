package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:52 PM
 **/

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String generateUserId() throws IOException {
        return userDAO.generateId();
    }

    @Override
    public boolean saveUser(UserDTO dto) throws IOException {
        return userDAO.save(new User(dto.getUserName(), dto.getName(), dto.getAddress(), dto.getDate(), dto.getPassword()));
    }

    @Override
    public String getPasswordByUserName(String text) throws IOException {
        return userDAO.getPassword(text);
    }

    @Override
    public ArrayList<UserDTO> getDetailsByUserName(String user_name) throws IOException {
        ArrayList<User> details = userDAO.getDetails(user_name);
        ArrayList<UserDTO> dto = new ArrayList<>();
        for (User detail : details) {
            dto.add(new UserDTO(
                    detail.getUserName(), detail.getName(), detail.getAddress(), detail.getDate(), detail.getPassword()
            ));
        }
        return dto;
    }

    @Override
    public boolean updateUserInfo(UserDTO dto) throws IOException {
        return userDAO.update(new User(dto.getUserName(), dto.getName(), dto.getAddress(), dto.getDate(), dto.getPassword()));
    }
}
