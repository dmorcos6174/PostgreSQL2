import hibernate.JoinsWithHQL;
import hibernate.services.CourseService;
import hibernate.services.InstructorService;
import hibernate.entities.Course;
import hibernate.entities.Instructor;
import hibernate.entities.Student;
import hibernate.HibernateUtil;

import java.sql.Timestamp;
import java.util.*;

import enums.*;

public class Main {
    public static void testInstructorHib() {
        // Create an InstructorDao object
        InstructorService instructorService = new InstructorService(HibernateUtil.getSessionFactory());

        // Create an InstructorHib object
        Instructor instructor = new Instructor(UUID.randomUUID(), "George", "Clooney", "george@example.com", "01211619424");

        // Save the instructor to the database
        instructorService.save(instructor);

        // Find the instructor by ID
        Instructor instructorById = instructorService.findById(instructor.getId());

        // Print the instructor to the console
        System.out.println("New Instructor Found By ID:");
        System.out.println(instructorById);

        List<Instructor> instructors = instructorService.findAll();
        System.out.println("All Instructors");
        for (Instructor ins : instructors) {
            System.out.println(ins);
        }

        // Delete the instructor
        instructorService.delete(instructorById);
    }

    public static void testCourseHib() {
        CourseService courseService = new CourseService(HibernateUtil.getSessionFactory());

        Course course = new Course(
                UUID.randomUUID(),
                "Java Programming",
                Timestamp.valueOf("2023-9-25 00:00:00"),
                Timestamp.valueOf("2023-10-25 00:00:00"),
                CourseLevel.Beginner,
                false,
                null
        );

        courseService.save(course);

        Course courseById = courseService.findById(course.getId());

        System.out.println("New Course Found By ID:");
        System.out.println(courseById);

        List<Course> courses = courseService.findAll();

        System.out.println("All Courses");
        for (Course crs : courses) {
            System.out.println(crs);
        }

        courseService.delete(courseById);
    }

    public static void testStudentsFieldCourseHib() {
        CourseService courseService = new CourseService(HibernateUtil.getSessionFactory());

        Course courseById = courseService.findById(UUID.fromString("7c8d9e0f-3a4b-5c6d-7e8f-9a0b1c2d3e4f"));
        System.out.println("Course Name: " + courseById.getName());

        Set<Student> students = courseById.getStudents();
        System.out.println("Enrolled Students:");
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    public static void main(String[] args) {

//        testInstructorHib(); // WORKS
//        testCourseHib(); // DOESNT
//        testStudentsFieldCourseHib(); // FIXED

//        getInstructorNameAndCourses();
//        getInstructorNamesAndStudents();
//        getCourseInfoAndStudents();
//        getStudentNamesInMiddleCourses();

//        JoinsWithHQL.getInstructorNamesAndCourses();
//        JoinsWithHQL.getInstructorNamesAndStudents();
//        JoinsWithHQL.getCourseNameStartDateAndStudents();
        JoinsWithHQL.getStudentsInMiddleLevel("Middle");

        // Close Session Factory
        HibernateUtil.closeSessionFactory();
    }
}
