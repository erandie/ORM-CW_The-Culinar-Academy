package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Students;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean update(User entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public User search(String id) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
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
