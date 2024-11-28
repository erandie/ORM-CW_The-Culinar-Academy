package lk.ijse.BO.custom.Implement;

import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.dto.StudentsDTO;
import lk.ijse.entity.Students;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentsDTO> getAllStudents() throws Exception {
        List<Students> students = studentDAO.getAll();
        List<StudentsDTO> allStudents = new ArrayList<>();

        for (Students student : studentDAO.getAll()) {
            allStudents.add(new StudentsDTO(
                    student.getStID(),
                    student.getStFullName(),
                    student.getStAddress(),
                    student.getStContact(),
                    student.getRegistrationDate(),
                    position, student.getUser()
            ));
        }
        return allStudents;
    }

    @Override
    public boolean addStudents(StudentsDTO dto) throws Exception {

        User user = dto.getUser();

        User user1 = new User();

        return studentDAO.add(new Students(
                dto.getStID(),
                user1,
                dto.getStFullName(),
                dto.getStAddress(),
                dto.getStContact(),
                dto.getRegistrationDate(),
                dto.getPosition()
        ));
    }

    @Override
    public boolean updateStudents(StudentsDTO dto) throws Exception {
        return studentDAO.update(new Students(
                dto.getStID(),
                dto.getUser(),
                dto.getStFullName(),
                dto.getStAddress(),
                dto.getStContact(),
                dto.getRegistrationDate(),
                dto.getPosition()
        ));
    }

    @Override
    public boolean deleteStudents(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public StudentsDTO searchStudent(String id) throws Exception {
        Students student = studentDAO.search(id);
        if (student != null) {
            return new StudentsDTO(
                    student.getStID(),
                    student.getStFullName(),
                    student.getStAddress(),
                    student.getStContact(),
                    student.getRegistrationDate()
            );
        }
        return null;
    }

    @Override
    public String generateNew_StudentID() throws Exception {
        return studentDAO.generateNewID();
    }

    @Override
    public boolean existStudent(String id) throws Exception {
        return studentDAO.exist(id);
    }
}