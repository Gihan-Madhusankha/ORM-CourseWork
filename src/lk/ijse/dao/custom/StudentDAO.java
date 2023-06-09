package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:56 PM
 **/

public interface StudentDAO extends CrudDAO<Student, String> {
    ArrayList<Student> getStudent(String id) throws IOException;
}
