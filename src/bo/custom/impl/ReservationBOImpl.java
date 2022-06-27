package bo.custom.impl;

import bo.custom.ReservationBO;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:35 PM
 **/

public class ReservationBOImpl implements ReservationBO {
    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public String generateRoomIdByRoomType(String typeId, String type) {
        return queryDAO.generateRoomNo(typeId, type);
    }
}
