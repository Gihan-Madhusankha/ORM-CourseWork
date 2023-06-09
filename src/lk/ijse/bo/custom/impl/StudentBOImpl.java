package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 7:53 PM
 **/

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

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
