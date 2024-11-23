package lk.ijse.DAO.custom.Implement;

import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Payments;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class PaymentDAOImpl implements PaymentDAO {
        @Override
        public boolean add(Payments entity) throws Exception {

            entity.setPaymentID(UUID.randomUUID().toString());

            Transaction transaction = null;

            try (Session session = FactoryConfiguration.getInstance().getSession()){
                transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();
                return true;
            }
        }

        @Override
        public boolean update(Payments entity) throws Exception {
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
                Payments payments = session.get(Payments.class, id);
                if (payments != null) {
                    session.delete(payments);
                    transaction.commit();
                    return true;
                }
                return false;
            }
        }

        @Override
        public Payments search(String id) throws Exception {
            try (Session session = FactoryConfiguration.getInstance().getSession()){
                return session.get(Payments.class, id);
            }
        }

        @Override
        public List<Payments> getAll() throws Exception {
            try (Session session = FactoryConfiguration.getInstance().getSession()){
                return session.createQuery("FROM Payments", Payments.class).list();
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
