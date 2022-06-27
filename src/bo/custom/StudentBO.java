package bo.custom;

import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:53 PM
 **/

public interface StudentBO {
    boolean saveStudent(StudentDTO dto);

    ArrayList<StudentDTO> getAllStudents();

    boolean deleteStudent(String id);

    boolean updateStudent(StudentDTO dto);

    ArrayList<StudentDTO> getStudentDetailsById(String id);
}
