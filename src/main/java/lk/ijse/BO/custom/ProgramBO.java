package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.ProgramsDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {

    public List<ProgramsDTO> getAllPrograms() throws Exception;

    public boolean addProgram(ProgramsDTO dto) throws Exception;

    public boolean UpdateProgram(ProgramsDTO dto) throws Exception;

    public boolean deleteProgram(String id) throws Exception;

    public ProgramsDTO searchProgram(String id) throws Exception;

    public boolean existProgram(String id) throws Exception;

    public String generateNew_ProgramID() throws Exception;


}
