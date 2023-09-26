import enums.CourseLevel;
import enums.Gender;
import postgresql.DAO.DataSourceConfiguration;
import postgresql.DAO.StudentDAO;
import postgresql.DTO.CourseStudent;
import postgresql.DTO.Instructor;
import postgresql.DTO.InstructorCourse;
import postgresql.DTO.InstructorStudent;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class TestDrivePSQL {
    public static void testInstructorPsql() {
        DataSource dataSource = DataSourceConfiguration.getInstance().getDataSource();

        // Create an instance of DAO.InstructorDAO with the DataSource
        postgresql.DAO.InstructorDAO instructorDAO = new postgresql.DAO.InstructorDAO(dataSource);

        // Create a sample DTO.Instructor object
        postgresql.DTO.Instructor instructor = new postgresql.DTO.Instructor(
                UUID.randomUUID(),
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890"
        );

        // Insert the instructor
        instructorDAO.insert(instructor, "instructor");

        // Update the instructor
        instructor.setFirstName("Updated John");
        instructorDAO.update(instructor, "instructor");

        // Retrieve the instructor by ID
        UUID instructorId = instructor.getId();
        postgresql.DTO.Instructor retrievedInstructor = instructorDAO.getByIdInstructor(instructorId, "instructor");
        System.out.println("Retrieved DTO.Instructor: \n" + retrievedInstructor);

        // Retrieve all instructors
        List<Instructor> allInstructors = instructorDAO.getAllInstructors("instructor");
        System.out.println("All Instructors:");
        for (postgresql.DTO.Instructor inst : allInstructors) {
            System.out.println(inst);
        }
        // Delete the instructor
        instructorDAO.delete(instructorId, "instructor");
    }

    public static void testCoursePsql() {
        DataSource dataSource = DataSourceConfiguration.getInstance().getDataSource();
        // Create an instance of DAO.CourseDAO with the DataSource
        postgresql.DAO.CourseDAO courseDAO = new postgresql.DAO.CourseDAO(dataSource);

        // Create a sample DTO.Course object
        postgresql.DTO.Course course = new postgresql.DTO.Course(
                UUID.randomUUID(),
                "Sample DTO.Course",
                Timestamp.valueOf("2023-09-15 10:00:00"),
                Timestamp.valueOf("2023-12-15 10:00:00"),
                CourseLevel.Middle,
                true,
                UUID.fromString("3dbf39a0-98b5-4f12-9df7-1d3c076d2204")
        );

        // Insert the course
        courseDAO.insert(course, "course");

        // Update the course
        course.setName("Updated DTO.Course");
        courseDAO.update(course, "course");

        // Retrieve the course by ID
        UUID courseId = course.getId();
        postgresql.DTO.Course retrievedCourse = courseDAO.getById(courseId, "course");
        System.out.println("Retrieved DTO.Course: \n" + retrievedCourse);

        List<postgresql.DTO.Course> allCourses = courseDAO.getAllCourses("course");
        System.out.println("All Courses:");
        for (postgresql.DTO.Course crs : allCourses) {
            System.out.println(crs);
        }

        // Delete the course
        courseDAO.delete(courseId, "course");
    }

    public static void testStudentPsql() {
        DataSource dataSource = DataSourceConfiguration.getInstance().getDataSource();
        // Create an instance of DAO.StudentDAO with the DataSource
        StudentDAO studentDAO = new StudentDAO(dataSource);

        // Create a sample DTO.Student object
        postgresql.DTO.Student student = new postgresql.DTO.Student(
                UUID.randomUUID(),
                "Alice",
                "Smith",
                20,
                Gender.FEMALE, // Assuming you have a DTO.GENDER enum defined
                "alice@example.com",
                "123-456-7890",
                45678901234562L
        );

        // Insert the student
        studentDAO.insert(student, "student");

        // Update the student
        student.setFirstName("Updated Alice");
        studentDAO.update(student, "student");

        // Retrieve the student by ID
        UUID studentId = student.getId();
        postgresql.DTO.Student retrievedStudent = studentDAO.getByIdInstructor(studentId, "student");
        System.out.println("Retrieved DTO.Student: \n" + retrievedStudent);

        // Retrieve all students
        List<postgresql.DTO.Student> allStudents = studentDAO.getAllStudents("student");
        System.out.println("All Students:");
        for (postgresql.DTO.Student stu : allStudents) {
            System.out.println(stu);
        }

        // Delete the student
        studentDAO.delete(studentId, "student");
    }

    public static void testJoinsPsql() {
        DataSource dataSource = DataSourceConfiguration.getInstance().getDataSource();

        // Create instances of your DAO classes.
        postgresql.DAO.InstructorDAO instructorDAO = new postgresql.DAO.InstructorDAO(dataSource);
        postgresql.DAO.CourseDAO courseDAO = new postgresql.DAO.CourseDAO(dataSource);
        StudentDAO studentDAO = new StudentDAO(dataSource);

        List<InstructorCourse> instructorCourses = instructorDAO.getInstructorCourseNamesAndIDs();
        System.out.println("Instructor Courses:");
        for (InstructorCourse dto : instructorCourses) {
            System.out.println(dto);
        }

        List<InstructorStudent> instructorStudents = instructorDAO.getInstructorStudentNames();
        System.out.println("\nInstructor Students:");
        for (InstructorStudent dto : instructorStudents) {
            System.out.println(dto);
        }

        List<CourseStudent> courseEnrollments = courseDAO.getCourseNamesStartDatesAndStudents();
        System.out.println("\nCourse Enrollments:");
        for (CourseStudent dto : courseEnrollments) {
            System.out.println(dto);
        }

        List<String> middleCourseStudents = studentDAO.getStudentsEnrolledInMiddleCourses();
        System.out.println("\nStudents Enrolled in Middle Courses:");
        for (String studentName : middleCourseStudents) {
            System.out.println(studentName);
        }
    }
}
