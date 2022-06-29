package bo;

import bo.custom.impl.ReservationBOImpl;
import dao.SuperDAO;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.ReservationDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.StudentDAOImpl;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 1:22 AM
 **/

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT, QUERY, RESERVATION, ROOM
    }

    public SuperDAO getBOTypes(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            default:
                return null;
        }
    }
}
