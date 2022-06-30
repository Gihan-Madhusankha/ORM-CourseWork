package dao;

import bo.SuperBO;
import bo.custom.impl.RoomBOImpl;
import bo.custom.impl.StudentBOImpl;
import dao.custom.impl.*;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:38 AM
 **/

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        ROOM, QUERY, RESERVATION, STUDENT, USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
