package hibernate.services;

import hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class StudentService {
    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        transaction.commit();
        session.close();

        return students;
    }

    public Student findById(UUID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();

        return student;
    }

    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
    }

    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);

        transaction.commit();
        session.close();
    }
}

