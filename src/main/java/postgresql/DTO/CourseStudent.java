package postgresql.DTO;

import java.sql.Timestamp;

public class CourseStudent {
    private String courseName;
    private Timestamp startDate;
    private String studentName;

    public CourseStudent(String courseName, Timestamp startDate, String studentName) {
        this.courseName = courseName;
        this.startDate = startDate;
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
