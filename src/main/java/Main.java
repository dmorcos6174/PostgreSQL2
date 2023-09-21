import hibernate.DAO.InstructorDAOHib;
import hibernate.DTO.InstructorHib;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import postgresql.DAO.CourseDAO;
import postgresql.DAO.DataSourceConfiguration;
import postgresql.DAO.InstructorDAO;
import postgresql.DAO.StudentDAO;
import postgresql.DTO.CourseStudent;
import postgresql.DTO.InstructorCourse;
import postgresql.DTO.InstructorStudent;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        DataSource dataSource = DataSourceConfiguration.getInstance().getDataSource();

//        // Create an instance of DAO.InstructorDAO with the DataSource
//        DAO.InstructorDAO instructorDAO = new DAO.InstructorDAO(dataSource);
//
//        // Create a sample DTO.Instructor object
//        DTO.Instructor instructor = new DTO.Instructor(
//                UUID.randomUUID(),
//                "John",
//                "Doe",
//                "john.doe@example.com",
//                "123-456-7890"
//        );
//
//        // Insert the instructor
//        instructorDAO.insert(instructor, "instructor");
//
//        // Update the instructor
//        instructor.setFirstName("Updated John");
//        instructorDAO.update(instructor, "instructor");
//
//        // Retrieve the instructor by ID
//        UUID instructorId = instructor.getId();
//        DTO.Instructor retrievedInstructor = instructorDAO.getByIdInstructor(instructorId, "instructor");
//        System.out.println("Retrieved DTO.Instructor: \n" + retrievedInstructor);
//
//        // Retrieve all instructors
//        List<DTO.Instructor> allInstructors = instructorDAO.getAllInstructors("instructor");
//        System.out.println("All Instructors:");
//        for (DTO.Instructor inst : allInstructors) {
//            System.out.println(inst);
//        }
//        // Delete the instructor
//        instructorDAO.delete(instructorId, "instructor");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // Create an instance of DAO.CourseDAO with the DataSource
//        DAO.CourseDAO courseDAO = new DAO.CourseDAO(dataSource);
//
//        // Create a sample DTO.Course object
//        DTO.Course course = new DTO.Course(
//                UUID.randomUUID(),
//                "Sample DTO.Course",
//                Timestamp.valueOf("2023-09-15 10:00:00"),
//                Timestamp.valueOf("2023-12-15 10:00:00"),
//                DTO.COURSE_LEVEL.Middle,
//                true,
//                UUID.fromString("3dbf39a0-98b5-4f12-9df7-1d3c076d2204")
//        );
//
//        // Insert the course
//        courseDAO.insert(course, "course");
//
//        // Update the course
//        course.setName("Updated DTO.Course");
//        courseDAO.update(course, "course");
//
//        // Retrieve the course by ID
//        UUID courseId = course.getId();
//        DTO.Course retrievedCourse = courseDAO.getById(courseId, "course");
//        System.out.println("Retrieved DTO.Course: \n" + retrievedCourse);
//
//        List<DTO.Course> allCourses = courseDAO.getAllCourses("course");
//        System.out.println("All Courses:");
//        for (DTO.Course crs : allCourses) {
//            System.out.println(crs);
//        }
//
//        // Delete the course
//        courseDAO.delete(courseId, "course");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // Create an instance of DAO.StudentDAO with the DataSource
//        DAO.StudentDAO studentDAO = new DAO.StudentDAO(dataSource);
//
//        // Create a sample DTO.Student object
//        DTO.Student student = new DTO.Student(
//                UUID.randomUUID(),
//                "Alice",
//                "Smith",
//                20,
//                DTO.GENDER.FEMALE, // Assuming you have a DTO.GENDER enum defined
//                "alice@example.com",
//                "123-456-7890",
//                45678901234562L
//        );
//
//        // Insert the student
//        studentDAO.insert(student, "student");
//
//        // Update the student
//        student.setFirstName("Updated Alice");
//        studentDAO.update(student, "student");
//
//        // Retrieve the student by ID
//        UUID studentId = student.getId();
//        DTO.Student retrievedStudent = studentDAO.getByIdInstructor(studentId, "student");
//        System.out.println("Retrieved DTO.Student: \n" + retrievedStudent);
//
//        // Retrieve all students
//        List<DTO.Student> allStudents = studentDAO.getAllStudents("student");
//        System.out.println("All Students:");
//        for (DTO.Student stu : allStudents) {
//            System.out.println(stu);
//        }
//
//        // Delete the student
//        studentDAO.delete(studentId, "student");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // Create instances of your DAO classes.
//        InstructorDAO instructorDAO = new InstructorDAO(dataSource);
//        CourseDAO courseDAO = new CourseDAO(dataSource);
//        StudentDAO studentDAO = new StudentDAO(dataSource);
//
//        List<InstructorCourse> instructorCourses = instructorDAO.getInstructorCourseNamesAndIDs();
//        System.out.println("Instructor Courses:");
//        for (InstructorCourse dto : instructorCourses) {
//            System.out.println(dto);
//        }
//
//        List<InstructorStudent> instructorStudents = instructorDAO.getInstructorStudentNames();
//        System.out.println("\nInstructor Students:");
//        for (InstructorStudent dto : instructorStudents) {
//            System.out.println(dto);
//        }
//
//        List<CourseStudent> courseEnrollments = courseDAO.getCourseNamesStartDatesAndStudents();
//        System.out.println("\nCourse Enrollments:");
//        for (CourseStudent dto : courseEnrollments) {
//            System.out.println(dto);
//        }
//
//        List<String> middleCourseStudents = studentDAO.getStudentsEnrolledInMiddleCourses();
//        System.out.println("\nStudents Enrolled in Middle Courses:");
//        for (String studentName : middleCourseStudents) {
//            System.out.println(studentName);
//        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Create an InstructorHib object
        InstructorHib instructor = new InstructorHib(UUID.randomUUID(), "Moataz", "Said", "moataz@example.com", "123-456-7890");

        // Create an InstructorDao object
        InstructorDAOHib instructorDao = new InstructorDAOHib(HibernateUtil.getSessionFactory());

        // Save the instructor to the database
        instructorDao.save(instructor);

        // Find all instructors in the database
        List<InstructorHib> instructors = instructorDao.findAll();

        // Print the instructors to the console
        for (InstructorHib instructor1 : instructors) {
            System.out.println(instructor1);
        }

        // Find the instructor by ID
        InstructorHib instructorById = instructorDao.findById(instructor.getId());

        // Print the instructor to the console
        System.out.println(instructorById);

        // Update the instructor
        instructorById.setFirstName("Jane");

        // Save the updated instructor to the database
        instructorDao.update(instructorById);

        // Find the instructor by ID again
        InstructorHib updatedInstructor = instructorDao.findById(instructor.getId());

        // Print the instructor to the console
        System.out.println(updatedInstructor);

        // Delete the instructor
        instructorDao.delete(instructorById);

        // Find the instructor by ID again
        InstructorHib deletedInstructor = instructorDao.findById(instructor.getId());

        // If the instructor is not null, then the delete operation failed
        if (deletedInstructor != null) {
            System.out.println("Delete operation failed");
        }
    }
}
