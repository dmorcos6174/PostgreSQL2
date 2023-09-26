package hibernate;

import enums.CourseLevel;
import hibernate.entities.Course;
import hibernate.entities.Instructor;
import hibernate.entities.Student;
import hibernate.services.CourseService;
import hibernate.services.InstructorService;

import java.util.List;
import java.util.Set;

public class JoinsWithHib {
    public static void getInstructorNamesAndCourses() {
        InstructorService instructorService = new InstructorService(HibernateUtil.getSessionFactory());

        List<Instructor> instructors = instructorService.findAll();

        for (Instructor ins: instructors) {
            System.out.println("Instructor Name: " + ins.getFirstName() + " " + ins.getLastName());
            List<Course> courses = ins.getCourses();
            System.out.println("Courses:");
            for (Course crs : courses) {
                System.out.println(crs.getName());
            }
        }
    }

    public static void getInstructorNamesAndStudents() {
        InstructorService instructorService = new InstructorService(HibernateUtil.getSessionFactory());

        List<Instructor> instructors = instructorService.findAll();

        for (Instructor ins : instructors) {
            System.out.println("Instructor Name: " + ins.getFirstName() + " " + ins.getLastName());
            List<Course> courses = ins.getCourses();
            System.out.println("Instructor Students: ");
            for (Course crs : courses) {
//                System.out.println("Course Name: " + crs.getName());
                Set<Student> students = crs.getStudents();
//                System.out.println("Enrolled Students");
                for (Student student : students) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
            }
        }
    }

    public static void getCourseInfoAndStudents() {
        CourseService courseService = new CourseService(HibernateUtil.getSessionFactory());
        List<Course> courses = courseService.findAll();

        for (Course crs : courses) {
            System.out.println("Course Name: " + crs.getName());
            System.out.println("Course Start Date: " + crs.getStartDate().toString());
            Set<Student> students = crs.getStudents();
            System.out.println("Enrolled Students: ");
            for (Student std : students) {
                System.out.println(std.getFirstName() + " " + std.getLastName());
            }
        }
    }

    public static void getStudentNamesInMiddleCourses() {
        CourseService courseService = new CourseService(HibernateUtil.getSessionFactory());
        List<Course> courses = courseService.findAll();

        System.out.println("Enrolled Students: ");
        for (Course crs : courses) {
            if (crs.getCourseLevel().equals(CourseLevel.Middle)) {
                Set<Student> students = crs.getStudents();
                for (Student std : students) {
                    System.out.println(std.getFirstName() + " " + std.getLastName());
                }
            }
        }
    }
}
