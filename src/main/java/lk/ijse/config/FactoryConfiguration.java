package lk.ijse.config;

import lk.ijse.entity.Payments;
import lk.ijse.entity.Programs;
import lk.ijse.entity.Students;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Students.class);
        configuration.addAnnotatedClass(Programs.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Payments.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null)? factoryConfiguration = new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
    
}
