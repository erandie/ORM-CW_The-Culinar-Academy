package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.StudentDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Students;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            }
            return false;
        }
    }

    @Override
    public Students search(String id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Students.class, id);
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
        return null;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }
}
