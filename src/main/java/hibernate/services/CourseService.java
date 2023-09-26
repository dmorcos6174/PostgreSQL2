package hibernate.services;

import hibernate.entities.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class CourseService {
    private final SessionFactory sessionFactory;

    public CourseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Course> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Course> courses = session.createQuery("FROM Course", Course.class).list();

        transaction.commit();
        session.close();

        return courses;
    }

    public Course findById(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, id);

        transaction.commit();
        session.close();

        return course;
    }

    public void save(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);

        transaction.commit();
        session.close();
    }

    public void update(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(course);

        transaction.commit();
        session.close();
    }

    public void delete(Course course) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(course);

        transaction.commit();
        session.close();
    }
}
