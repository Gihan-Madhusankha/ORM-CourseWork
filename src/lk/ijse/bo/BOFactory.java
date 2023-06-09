package lk.ijse.bo;

import lk.ijse.bo.custom.impl.ReservationBOImpl;
import lk.ijse.bo.custom.impl.RoomBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

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
        STUDENT, RESERVATION, ROOM, USER
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
