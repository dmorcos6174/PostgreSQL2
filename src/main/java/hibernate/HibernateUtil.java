package hibernate;

import hibernate.entities.Course;
import hibernate.entities.InstructorDetails;
import hibernate.entities.Instructor;
import hibernate.entities.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Properties properties;

    private HibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        // OLD
//        if (sessionFactory == null) {
//            properties = new Properties();
//            try {
//                properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
//                sessionFactory = new Configuration().setProperties(properties).buildSessionFactory();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return sessionFactory;
        // NEW
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(InstructorDetails.class);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
