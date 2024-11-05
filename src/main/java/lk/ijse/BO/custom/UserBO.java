package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {

    public List<UserDTO> getAllUsers() throws Exception;

    public boolean addUsers(UserDTO dto) throws Exception;

    public boolean updateUser(UserDTO dto) throws Exception;

    public boolean deleteUser(String id) throws Exception;

    public UserDTO searchUser(String id) throws Exception;

}
