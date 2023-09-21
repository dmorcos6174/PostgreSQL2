package postgresql.DTO;

public class InstructorStudent {
    private String instructorName;
    private String studentName;

    public InstructorStudent(String instructorName, String studentName) {
        this.instructorName = instructorName;
        this.studentName = studentName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "InstructorStudent{" +
                "instructorName='" + instructorName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
