package dao.custom;

import dao.CrudDAO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:56 PM
 **/

public interface StudentDAO extends CrudDAO<Student, String> {
    ArrayList<Student> getStudent(String id);
}
