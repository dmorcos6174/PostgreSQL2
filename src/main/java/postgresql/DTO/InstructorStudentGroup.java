package postgresql.DTO;

import java.util.List;

public class InstructorStudentGroup {
    private Instructor instructor;
    private List<Student> students;

    public InstructorStudentGroup(Instructor instructor, List<Student> students) {
        this.instructor = instructor;
        this.students = students;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

//    @Override
//    public String toString() {
//        return "InstructorStudentGroup{" +
//                "instructor=" + instructor +
//                ", students=" + students +
//                '}';
//    }
}
