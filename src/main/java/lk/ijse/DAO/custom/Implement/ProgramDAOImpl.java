package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.ProgramsDAO;
import lk.ijse.dto.ProgramsDTO;

import java.util.List;

public class ProgramDAOImpl implements ProgramsDAO {
    @Override
    public boolean add(ProgramsDTO entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(ProgramsDTO entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public ProgramsDTO search(String id) throws Exception {
        return null;
    }

    @Override
    public List<ProgramsDTO> getAll() throws Exception {
        return null;
    }

    @Override
    public String generateNewID() throws Exception {
        return null;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }
}
