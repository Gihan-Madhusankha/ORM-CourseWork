package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:56 PM
 **/

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() {
        return null;
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
