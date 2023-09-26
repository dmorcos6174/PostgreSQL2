package postgresql.DTO;

import enums.CourseLevel;

import java.sql.Timestamp;
import java.util.UUID;

public class Course {

    private UUID id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private CourseLevel courseLevel;
    private boolean isStarted;
    private UUID instructorId;

    public Course(UUID id, String name, Timestamp startDate, Timestamp endDate, CourseLevel courseLevel, boolean isStarted, UUID instructorId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseLevel = courseLevel;
        this.isStarted = isStarted;
        this.instructorId = instructorId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(CourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public UUID getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(UUID instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "------------DTO.Course------------" + '\n' +
                "id=" + id + '\n' +
                "name=" + name + '\n' +
                "startDate=" + startDate + '\n' +
                "endDate=" + endDate + '\n' +
                "courseLevel=" + courseLevel + '\n' +
                "isStarted=" + isStarted + '\n' +
                "instructorId=" + instructorId + '\n' +
                "------------------------";
    }
}
