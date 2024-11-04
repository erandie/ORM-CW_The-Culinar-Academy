package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.ProgramsDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramDAOImpl implements ProgramsDAO {
    @Override
    public boolean add(Programs entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean update(Programs entity) throws Exception {
       try (Session session = FactoryConfiguration.getInstance().getSession()){
           Transaction transaction = session.beginTransaction();
           session.update(entity);
           transaction.commit();
           return true;
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
        try (Session session = FactoryConfiguration.getInstance().getSession()){
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
        return null;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }
}
