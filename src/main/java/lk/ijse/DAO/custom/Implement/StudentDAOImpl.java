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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Students entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("delete from Students where stId ='"+id+"'", Students.class).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Students search(String id) {
        return null;
    }

    @Override
    public List<Students> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Students> studentsList = session.createNativeQuery("SELECT * FROM Students", Students.class).list();
        transaction.commit();
        session.close();
        return studentsList;
    }

    @Override
    public String generateNewID() {
        return null;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }
}
