package postgresql.DAO;

import postgresql.DTO.Instructor;
import postgresql.DTO.InstructorCourse;
import postgresql.DTO.InstructorStudent;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstructorDAO extends BaseDAO<Instructor> {

    public InstructorDAO() {
        super();
    }

    public InstructorDAO(DataSource dataSource) {
        super(dataSource);
    }

    public List<Instructor> getAllInstructors(String tableName) {
        return this.getAll("instructor");
    }

    public Instructor getByIdInstructor(UUID id, String tableName) {
        return this.getById(id, "instructor");
    }

    @Override
    public void insert(Instructor instructor, String tableName) {
        String insertQuery = getInsertQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(insertQuery)) {
            statement.setObject(1, instructor.getId());
            statement.setString(2, instructor.getFirstName());
            statement.setString(3, instructor.getLastName());
            statement.setString(4, instructor.getEmail());
            statement.setString(5, instructor.getPhoneNum());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Instructor instructor, String tableName) {
        String updateQuery = getUpdateQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, instructor.getFirstName());
            statement.setString(2, instructor.getLastName());
            statement.setString(3, instructor.getEmail());
            statement.setString(4, instructor.getPhoneNum());
            statement.setObject(5, instructor.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getInsertQuery(String tableName) {
        return "INSERT INTO " + tableName + " (id, first_name, last_name, email, phone_num) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery(String tableName) {
        return "UPDATE " + tableName + " SET first_name = ?, last_name = ?, email = ?, phone_num = ? WHERE id = ?";
    }

    @Override
    protected Instructor mapResultSetToDTO(ResultSet resultSet) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phoneNum = resultSet.getString("phone_num");

        return new Instructor(id, firstName, lastName, email, phoneNum);
    }

    // Retrieves all instructors' full names along with the name and ID of courses they teach.
    public List<InstructorCourse> getInstructorCourseNamesAndIDs() {
        List<InstructorCourse> result = new ArrayList<>();
        String sql = "SELECT " +
                "    i.first_name || ' ' || i.last_name AS instructor_name, " +
                "    c.id AS course_id, " +
                "    c.name AS course_name " +
                "FROM " +
                "    public.instructor AS i " +
                "LEFT JOIN " +
                "    public.course AS c ON i.id = c.instructor_id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String instructorName = resultSet.getString("instructor_name");
                UUID courseId = (UUID) resultSet.getObject("course_id");
                String courseName = resultSet.getString("course_name");

                InstructorCourse dto = new InstructorCourse(instructorName, courseId, courseName);
                result.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Retrieves all instructors' full names along with the names of students enrolled in courses they teach.
    public List<InstructorStudent> getInstructorStudentNames() {
        List<InstructorStudent> result = new ArrayList<>();
        String sql = "SELECT " +
                "    i.first_name || ' ' || i.last_name AS instructor_name, " +
                "    s.first_name || ' ' || s.last_name AS student_name " +
                "FROM " +
                "    public.instructor AS i " +
                "INNER JOIN " +
                "    public.course AS c ON i.id = c.instructor_id " +
                "INNER JOIN " +
                "    public.student_course AS sc ON c.id = sc.enrolled_course " +
                "INNER JOIN " +
                "    public.student AS s ON sc.student_id = s.id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String instructorName = resultSet.getString("instructor_name");
                String studentName = resultSet.getString("student_name");

                InstructorStudent dto = new InstructorStudent(instructorName, studentName);
                result.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
