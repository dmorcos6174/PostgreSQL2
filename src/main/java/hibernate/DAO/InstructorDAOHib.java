package hibernate.DAO;

import hibernate.DTO.InstructorHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import postgresql.DTO.Instructor;

import java.util.List;
import java.util.UUID;

public class InstructorDAOHib {
    private final SessionFactory sessionFactory;

    public InstructorDAOHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<InstructorHib> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<InstructorHib> instructors = session.createQuery("FROM InstructorHib", InstructorHib.class).list();

        transaction.commit();
        session.close();

        return instructors;
    }

    public InstructorHib findById(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        InstructorHib instructor = session.get(InstructorHib.class, id);

        transaction.commit();
        session.close();

        return instructor;
    }

    public void save(InstructorHib instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(instructor);

        transaction.commit();
        session.close();
    }

    public void update(InstructorHib instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(instructor);

        transaction.commit();
        session.close();
    }

    public void delete(InstructorHib instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(instructor);

        transaction.commit();
        session.close();
    }
}
