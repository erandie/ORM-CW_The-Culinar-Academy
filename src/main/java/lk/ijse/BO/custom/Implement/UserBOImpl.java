package lk.ijse.BO.custom.Implement;

import lk.ijse.BO.custom.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<UserDTO> allUsers = new ArrayList<>();
        for (User user : userDAO.getAll()) {
            allUsers.add(new UserDTO(
                    user.getUserID(),
                    user.getName(),
                    user.getEmail(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getPosition()
            ));
        }
        return allUsers;
    }

    @Override
    public boolean addUsers(UserDTO dto) throws Exception {
        return userDAO.add(new User(
                dto.getUserID(),
                dto.getName(),
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getPosition()
        ));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(
                dto.getUserID(),
                dto.getName(),
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getPosition()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public UserDTO searchUser(String id) throws Exception {
        User user = userDAO.search(id);
        if (user != null) {
            return new UserDTO(
                    user.getUserID(),
                    user.getName(),
                    user.getEmail(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getPosition()
            );
        }
        return null;
    }

    @Override
    public boolean existUser(String id) throws Exception {
        return userDAO.exist(id);
    }

    @Override
    public String generateNew_UserID() throws Exception {
        return userDAO.generateNewID();
    }
}
