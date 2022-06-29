package dao.custom.impl;

import dao.custom.QueryDAO;

/**
 * @author : Gihan Madhusankha
 * 2022-06-27 1:38 PM
 **/

public class QueryDAOImpl implements QueryDAO {
    /*@Override
    public String generateRoomNo(String typeId, String type) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT r.resId FROM Reservation r " +
                "LEFT JOIN Room rm ON r.room = rm.roomTypeId\n" +
                "WHERE rm.type = :rType ORDER BY r.resId DESC";

        Query query = session.createQuery(hql);
        query.setParameter("rType", type);
        List<String> list = query.list();

        String s = null;
        for (String ss : list) {
            s = ss;
        }

        transaction.commit();
        session.close();

        if (type.equals("Non-AC")) {
            if (s != null) {
                int rmId = Integer.parseInt(s.replace("RM_NA-", "")) + 1;
                return String.format("RM_NA-%03d", rmId);
            } else {
                return "RM_NA-001";
            }

        } else if (type.equals("Non-AC / Food")) {
            if (s != null) {
                int rmId = Integer.parseInt(s.replace("RM_NAF-", "")) + 1;
                return String.format("RM_NAF-%03d", rmId);
            } else {
                return "RM_NAF-001";
            }
        } else if (type.equals("AC")) {
            if (s != null) {
                int rmId = Integer.parseInt(s.replace("RM_A-", "")) + 1;
                return String.format("RM_A%03d", rmId);
            } else {
                return "RM_A-001";
            }

        } else {
            if (s != null) {
                int rmId = Integer.parseInt(s.replace("RM_AF-", "")) + 1;
                return String.format("RM_AF%03d", rmId);
            } else {
                return "RM_AF-001";
            }
        }

    }*/
}
