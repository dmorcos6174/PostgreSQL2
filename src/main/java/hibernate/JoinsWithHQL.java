package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class JoinsWithHQL {
    public static void getInstructorNamesAndCourses() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT i.firstName, i.lastName, c.name FROM Instructor i JOIN i.courses c");
        List<Object[]> results = query.list();

        for (Object[] result : results) {
            String firstName = (String) result[0];
            String lastName = (String) result[1];
            String courseName = (String) result[2];

            System.out.println(firstName + " " + lastName + ": " + courseName);
        }
        session.close();
    }

    public static void getInstructorNamesAndStudents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT i.firstName, i.lastName, s.firstName, s.lastName FROM Instructor i JOIN i.courses c JOIN c.students s");
        List<Object[]> results = query.list();

        for (Object[] result : results) {
            String instructorFirstName = (String) result[0];
            String instructorLastName = (String) result[1];
            String studentFirstName = (String) result[2];
            String studentLastName = (String) result[3];

            System.out.println(instructorFirstName + " " + instructorLastName + ": " + studentFirstName + " " + studentLastName);
        }
    }

    public static void getCourseNameStartDateAndStudents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c.name, c.startDate, s.firstName, s.lastName FROM Course c JOIN c.students s");
        List<Object[]> results = query.list();

        for (Object[] result : results) {
            String courseName = (String) result[0];
            String courseStartDate = result[1].toString();
            String studentFirstName = (String) result[2];
            String studentLastName = (String) result[3];

            System.out.println(courseName + " " + courseStartDate + " " + studentFirstName + " " + studentLastName);
        }
    }

    public static void getStudentsInMiddleLevel(String courseLevel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT s.firstName, s.lastName FROM Student s JOIN s.courses c WHERE c.courseLevel = Middle");
//        query.setParameter(1, courseLevel);

        List<Object[]> results = query.list();

        for (Object[] result : results) {
            String firstName = (String) result[0];
            String lastName = (String) result[1];

            System.out.println(firstName + " " + lastName);
        }
    }
}
