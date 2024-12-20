package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    public List<UserDTO> getAllUsers() throws Exception;

    public boolean addUsers(UserDTO dto) throws Exception;

    public boolean updateUser(UserDTO dto) throws Exception;

    public boolean deleteUser(String id) throws Exception;

    public UserDTO searchUser(String id) throws Exception;

    public boolean existUser(String id) throws Exception;

    public String generateNew_UserID() throws Exception;

    boolean checkCredentials(String userId, String password) throws SQLException;
}
