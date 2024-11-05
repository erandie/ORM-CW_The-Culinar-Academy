package lk.ijse.BO.custom.Implement;

import lk.ijse.BO.custom.StudentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.entity.Students;
import lk.ijse.dto.StudentsDTO;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentsDTO> getAllStudents() throws Exception {
        List<StudentsDTO> allStudents = new ArrayList<>();
        for (Students student : studentDAO.getAll()) {
            allStudents.add(new StudentsDTO(
                    student.getStID(),
                    student.getStFullName(),
                    student.getStAddress(),
                    student.getStContact(),
                    student.getRegistrationDate()
            ));
        }
        return allStudents;
    }

    @Override
    public boolean addStudents(StudentsDTO dto) throws Exception {
        return studentDAO.add(new Students(
                dto.getStID(),
                dto.getStFullName(),
                dto.getStAddress(),
                dto.getStContact(),
                dto.getRegistrationDate()
        ));
    }

    @Override
    public boolean updateStudents(StudentsDTO dto) throws Exception {
        return studentDAO.update(new Students(
                dto.getStID(),
                dto.getStFullName(),
                dto.getStAddress(),
                dto.getStContact(),
                dto.getRegistrationDate()
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
}
