package postgresql.DTO;

import java.util.UUID;

public class InstructorCourse {
    private String instructorName;
    private UUID courseId;
    private String courseName;

    public InstructorCourse(String instructorName, UUID courseId, String courseName) {
        this.instructorName = instructorName;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "InstructorCourse{" +
                "instructorName='" + instructorName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
