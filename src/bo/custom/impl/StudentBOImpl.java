package bo.custom.impl;

import bo.BOFactory;
import bo.custom.StudentBO;
import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:53 PM
 **/

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getContactNo(),
                dto.getDob(), dto.getGender()));
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        for (Student student : all) {
            allStudents.add(new StudentDTO(
                    student.getId(), student.getName(), student.getAddress(), student.getContactNo(),
                    student.getDob(), student.getGender()
            ));
        }
        return allStudents;
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(dto.getId(), dto.getName(), dto.getAddress(), dto.getContactNo(),
                dto.getDob(), dto.getGender()));
    }

    @Override
    public ArrayList<StudentDTO> getStudentDetailsById(String id) throws IOException {
        ArrayList<Student> student = studentDAO.getStudent(id);
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : student) {
            studentDTOS.add(new StudentDTO(
                    s.getId(), s.getName(), s.getAddress(), s.getContactNo(), s.getDob(), s.getGender()
            ));
        }
        return studentDTOS;
    }
}
