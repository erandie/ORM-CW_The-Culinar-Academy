package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.ProgramsDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl implements ProgramsDAO {
    @Override
    public boolean add(Programs entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Programs entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            Programs programs = session.get(Programs.class, id);

            if (programs != null) {
                session.delete(programs);
                transaction.commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public Programs search(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Returning the Program entity by searching with the program ID
            return session.get(Programs.class, id);
        }
    }


    @Override
    public List<Programs> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.createQuery("FROM Programs", Programs.class).list();
        }
    }

    @Override
    public String generateNewID() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            String lastID = (String) session.createQuery("SELECT MAX(programID) FROM Programs").uniqueResult();
            transaction.commit();

            if (lastID != null) {
                int newID = Integer.parseInt(lastID.replace("P", "")) + 1;
                return String.format("P%03d", newID);
            } else {
                return "P001"; // Default ID if no students exist
            }
        }
    }
    @Override
    public boolean exist(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to check if the entity exists
            Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Programs p WHERE p.programID = :id", Long.class);
            query.setParameter("id", id);

            // Execute the query and get the result
            Long count = query.uniqueResult();
            return count > 0; // Return true if count is greater than 0
        } catch (Exception e) {
            throw new Exception("Failed to check existence of the program with ID: " + id, e);
        }
    }
}