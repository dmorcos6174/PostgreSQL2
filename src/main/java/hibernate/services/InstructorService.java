package hibernate.services;

import hibernate.entities.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class InstructorService {
    private final SessionFactory sessionFactory;

    public InstructorService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Instructor> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Instructor> instructors = session.createQuery("FROM Instructor", Instructor.class).list();

//        transaction.commit();
        session.close();

        return instructors;
    }
    public List<String> findAllNames(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<String> instructorNames = session.createQuery("select ins.firstName FROM Instructor ins " +
                "where ins.firstName like 'J%' ", String.class).list();

//        transaction.commit();
        session.close();

        return instructorNames;
    }

    public List findAllYoutubeChannels(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List instructorNames = session.createQuery("select ins.firstName, ins.lastName, ins.instructorDetails.youtubeChannel FROM Instructor ins", String.class).list();

//        transaction.commit();
        session.close();

        return instructorNames;
    }

    public Instructor findById(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Instructor instructor = session.get(Instructor.class, id);

//        transaction.commit();
        session.close();

        return instructor;
    }

    public void save(Instructor instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(instructor);

        transaction.commit();
        session.close();
    }

    public void update(Instructor instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(instructor);

        transaction.commit();
        session.close();
    }

    public void delete(Instructor instructor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(instructor);

        transaction.commit();
        session.close();
    }
}
