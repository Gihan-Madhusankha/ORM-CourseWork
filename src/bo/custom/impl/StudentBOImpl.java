package bo.custom.impl;

import bo.custom.StudentBO;
import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:53 PM
 **/

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getContactNo(),
                dto.getDate(), dto.getGender()));
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student student : all) {
            allStudents.add(new StudentDTO(
                    student.getId(), student.getName(), student.getAddress(), student.getContactNo(),
                    student.getDate(), student.getGender()
            ));
        }
        return allStudents;
    }
}
