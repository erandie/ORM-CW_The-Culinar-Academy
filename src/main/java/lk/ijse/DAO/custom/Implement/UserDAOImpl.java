package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.UserDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Students;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery("FROM User WHERE userID = :id", User.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public String generateNewID() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            String lastID = (String) session.createQuery("SELECT MAX(userID) FROM User ").uniqueResult();
            transaction.commit();

            if (lastID != null) {
                int newID = Integer.parseInt(lastID.replace("U", "")) + 1;
                return String.format("U%03d", newID);
            } else {
                return "U001";
            }
        }
    }

    @Override
    public boolean exist(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to check if the entity exists
            Query<Long> query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.userID = :id", Long.class);
            query.setParameter("id", id);

            // Execute the query and get the result
            Long count = query.uniqueResult();
            return count > 0; // Return true if count is greater than 0
        } catch (Exception e) {
            throw new Exception("Failed to check existence of the student with ID: " + id, e);
        }
    }

    @Override
    public String getPasswordHashByUserId(String userName) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT u.password FROM User u WHERE u.userName = :userName";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("userName", userName); // Ensure consistency here
            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("Error fetching password hash: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
