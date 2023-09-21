package postgresql.DAO;

import postgresql.DTO.GENDER;
import postgresql.DTO.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDAO extends BaseDAO<Student> {
    public StudentDAO() {
        super();
    }

    public StudentDAO(DataSource dataSource) {
        super(dataSource);
    }

    public List<Student> getAllStudents(String tableName) {
        return this.getAll("student");
    }

    public Student getByIdInstructor(UUID id, String tableName) {
        return this.getById(id, "student");
    }

    @Override
    public void insert(Student student, String tableName) {
        String insertQuery = getInsertQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(insertQuery)) {
            statement.setObject(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getGender().toString());
            statement.setString(6, student.getEmail());
            statement.setString(7, student.getPhoneNum());
            statement.setLong(8, student.getNatId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student, String tableName) {
        String updateQuery = getUpdateQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getGender().toString());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhoneNum());
            statement.setLong(7, student.getNatId());
            statement.setObject(8, student.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getInsertQuery(String tableName) {
        return "INSERT INTO " + tableName + " (id, first_name, last_name, age, gender, email, phone_num, nat_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery(String tableName) {
        return "UPDATE " + tableName + " SET first_name = ?, last_name = ?, age = ?, gender = ?, email = ?, phone_num = ?, nat_id = ? WHERE id = ?";
    }

    @Override
    protected Student mapResultSetToDTO(ResultSet resultSet) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer age = resultSet.getInt("age");
        GENDER gender = GENDER.valueOf(resultSet.getString("gender"));
        String email = resultSet.getString("email");
        String phoneNum = resultSet.getString("phone_num");
        Long natId = resultSet.getLong("nat_id");

        return new Student(id, firstName, lastName, age, gender, email, phoneNum, natId);
    }

    public List<String> getStudentsEnrolledInMiddleCourses() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT " +
                "    s.first_name || ' ' || s.last_name AS student_name " +
                "FROM " +
                "    public.student AS s " +
                "INNER JOIN " +
                "    public.student_course AS sc ON s.id = sc.student_id " +
                "INNER JOIN " +
                "    public.course AS c ON sc.enrolled_course = c.id " +
                "WHERE " +
                "    c.course_level = 'Middle'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String studentName = resultSet.getString("student_name");
                result.add(studentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
