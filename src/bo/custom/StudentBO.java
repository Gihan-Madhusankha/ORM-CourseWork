package bo.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:53 PM
 **/

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO dto) throws IOException;

    ArrayList<StudentDTO> getAllStudents() throws IOException;

    boolean deleteStudent(String id) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    ArrayList<StudentDTO> getStudentDetailsById(String id) throws IOException;
}
