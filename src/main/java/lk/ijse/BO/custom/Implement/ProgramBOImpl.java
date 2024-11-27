package lk.ijse.BO.custom.Implement;

import lk.ijse.BO.custom.ProgramBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.ProgramsDAO;
import lk.ijse.dto.ProgramsDTO;
import lk.ijse.entity.Programs;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramsDAO programsDAO = (ProgramsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public List<ProgramsDTO> getAllPrograms() throws Exception {
        List<ProgramsDTO> allPrograms = new ArrayList<>();
        for (Programs programs : programsDAO.getAll()) {
            allPrograms.add(new ProgramsDTO(
                    programs.getProgramID(),
                    programs.getPrName(),
                    programs.getDuration(),
                    programs.getPrFee()
            ));
        }
        return allPrograms;
    }

    @Override
    public boolean addProgram(ProgramsDTO dto) throws Exception {
        return programsDAO.add(new Programs(
                dto.getProgramID(),
                dto.getPrName(),
                dto.getDuration(),
                dto.getPrFee()
        ));
    }

    @Override
    public boolean UpdateProgram(ProgramsDTO dto) throws Exception {
        return programsDAO.update(new Programs(
                dto.getProgramID(),
                dto.getPrName(),
                dto.getDuration(),
                dto.getPrFee()
        ));
    }

    @Override
    public boolean deleteProgram(String id) throws Exception {
        return programsDAO.delete(id);
    }

    @Override
    public ProgramsDTO searchProgram(String id) throws Exception {
        Programs programs = programsDAO.search(id);
        if (programs != null) {
            return new ProgramsDTO(
                    programs.getProgramID(),
                    programs.getPrName(),
                    programs.getDuration(),
                    programs.getPrFee()
            );
        }
        return null;
    }

    @Override
    public boolean existProgram(String id) throws Exception {
        return programsDAO.exist(id);
    }

    @Override
    public String generateNew_ProgramID() throws Exception {
        return programsDAO.generateNewID();
    }
}
