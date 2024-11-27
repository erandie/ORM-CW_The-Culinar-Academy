package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.StudentsDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    public List<StudentsDTO> getAllStudents() throws Exception;

    public boolean addStudents(StudentsDTO dto) throws Exception;

    public boolean updateStudents(StudentsDTO dto) throws Exception;

    public boolean deleteStudents(String id) throws Exception;

    public StudentsDTO searchStudent(String id) throws Exception;

    public String generateNew_StudentID() throws Exception;

    public boolean existStudent(String id) throws Exception;
}