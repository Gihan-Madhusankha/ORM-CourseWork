package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:56 PM
 **/

public class StudentDAOImpl implements StudentDAO {

    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        List<Student> list = session.createQuery(hql).list();
        ArrayList<Student> allStudents = new ArrayList<>();
        for (Student student : list) {
            allStudents.add(new Student(
                    student.getId(), student.getName(), student.getAddress(), student.getContactNo(),
                    student.getDate(), student.getGender()
            ));
        }
        transaction.commit();
        session.close();
        return allStudents;
    }

    @Override
    public boolean save(Student entity) {
        Student student = new Student();
        student.setId(entity.getId());
        student.setName(entity.getName());
        student.setAddress(entity.getAddress());
        student.setContactNo(entity.getContactNo());
        student.setGender(entity.getGender());
        student.setDate(entity.getDate());

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Student SET name = :stName, address = :stAddress, contactNo = :stContactNo, date = :stDate, gender = :stGender WHERE id = :stId";
        Query query = session.createQuery(hql);
        query.setParameter("stName", entity.getName());
        query.setParameter("stAddress", entity.getAddress());
        query.setParameter("stContactNo", entity.getContactNo());
        query.setParameter("stDate", entity.getDate());
        query.setParameter("stGender", entity.getGender());
        query.setParameter("stId", entity.getId());
        boolean b = query.executeUpdate() > 0;

        transaction.commit();
        session.close();
        return b;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Student del = session.load(Student.class, s);
        session.delete(del);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public String generateId() {
        return null;
    }

    @Override
    public Student search(String s) {
        return null;
    }
}
