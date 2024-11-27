package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Students;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Students entity) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean update(Students entity) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Students student = session.get(Students.class, id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                return true;
            } else {
                System.out.println("Student not found with ID: " + id);
                return false;
            }
        }
    }


    @Override
    public Students search(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Students> query = session.createQuery("FROM Students WHERE stID = :id", Students.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Error while searching for student with ID: " + id, e);
        }
    }



    @Override
    public List<Students> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Students", Students.class).list();
        }
    }

    @Override
    public String generateNewID() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            String lastID = (String) session.createQuery("SELECT MAX(stID) FROM Students").uniqueResult();
            transaction.commit();

            if (lastID != null) {
                int newID = Integer.parseInt(lastID.replace("S", "")) + 1;
                return String.format("S%03d", newID);
            } else {
                return "S001"; // Default ID if no students exist
            }
        }

    }


    @Override
    public boolean exist(String id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Use HQL to check if the entity exists
            Query<Long> query = session.createQuery("SELECT COUNT(s) FROM Students s WHERE s.stID = :id", Long.class);
            query.setParameter("id", id);

            // Execute the query and get the result
            Long count = query.uniqueResult();
            return count > 0; // Return true if count is greater than 0
        } catch (Exception e) {
            throw new Exception("Failed to check existence of the student with ID: " + id, e);
        }
    }

}
