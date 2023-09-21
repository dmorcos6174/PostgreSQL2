package postgresql.DAO;

import postgresql.DTO.COURSE_LEVEL;
import postgresql.DTO.Course;
import postgresql.DTO.CourseStudent;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseDAO extends BaseDAO<Course>{
    public CourseDAO(){
        super();
    }

    public CourseDAO(DataSource dataSource){
        super(dataSource);
    }

    public List<Course> getAllCourses(String tableName){
        return this.getAll("course");
    }

    public Course getByIdCourse(UUID id, String tableName){
        return this.getById(id, "course");
    }

    @Override
    public void insert(Course course, String tableName) {
        String insertQuery = getInsertQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(insertQuery)) {
            statement.setObject(1, course.getId());
            statement.setString(2, course.getName());
            statement.setTimestamp(3, course.getStartDate());
            statement.setTimestamp(4, course.getEndDate());
            statement.setString(5, course.getCourseLevel().toString());
            statement.setBoolean(6, course.isStarted());
            statement.setObject(7, course.getInstructorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course course, String tableName) {
        String updateQuery = getUpdateQuery(tableName);
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, course.getName());
            statement.setTimestamp(2, course.getStartDate());
            statement.setTimestamp(3, course.getEndDate());
            statement.setString(4, course.getCourseLevel().toString());
            statement.setBoolean(5, course.isStarted());
            statement.setObject(6, course.getInstructorId());
            statement.setObject(7, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getInsertQuery(String tableName) {
        return "INSERT INTO " + tableName + " (id, name, start_date, end_date, course_level, is_started, instructor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery(String tableName) {
        return "UPDATE " + tableName + " SET name = ?, start_date = ?, end_date = ?, course_level = ?, " +
                "is_started = ?, instructor_id = ? WHERE id = ?";
    }

    @Override
    protected Course mapResultSetToDTO(ResultSet resultSet) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String name = resultSet.getString("name");
        Timestamp startDate = resultSet.getTimestamp("start_date");
        Timestamp endDate = resultSet.getTimestamp("end_date");
        COURSE_LEVEL courseLevel = COURSE_LEVEL.valueOf(resultSet.getString("course_level"));
        boolean isStarted = resultSet.getBoolean("is_started");
        UUID instructorId = (UUID) resultSet.getObject("instructor_id");

        return new Course(id, name, startDate, endDate, courseLevel, isStarted, instructorId);
    }

    // Retrieves the names of courses, their start dates, and the names of students enrolled in each course.
    public List<CourseStudent> getCourseNamesStartDatesAndStudents() {
        List<CourseStudent> result = new ArrayList<>();
        String sql = "SELECT " +
                "    c.name AS course_name, " +
                "    c.start_date, " +
                "    s.first_name || ' ' || s.last_name AS student_name " +
                "FROM " +
                "    public.course AS c " +
                "LEFT JOIN " +
                "    public.student_course AS sc ON c.id = sc.enrolled_course " +
                "LEFT JOIN " +
                "    public.student AS s ON sc.student_id = s.id";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                Timestamp startDate = resultSet.getTimestamp("start_date");
                String studentName = resultSet.getString("student_name");

                CourseStudent dto = new CourseStudent(courseName, startDate, studentName);
                result.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}