package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:56 PM
 **/

public class StudentDAOImpl implements StudentDAO {
    Session session = FactoryConfiguration.getFactoryConfiguration().getSession();

    @Override
    public ArrayList<Student> getAll() {
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

        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
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
