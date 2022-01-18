import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

public class Course {
    private String name;

    private Teacher titulary;

    private List<Assistant> assistants;

    private SortedMap<Grade, Student> studentsGrades;

    private Strategy strategy;

    public Course(CourseBuilder courseBuilder) {
        this.name = courseBuilder.name;
        this.titulary = courseBuilder.titulary;
        this.assistants = courseBuilder.assistants;
        this.studentsGrades = courseBuilder.studentsGrades;
        this.strategy = courseBuilder.strategy;
    }

    public void addGrade(Student student, Grade grade) {
        studentsGrades.put(grade, student);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course name ").append(name).append("\n");
        sb.append("Teacher: ").append(titulary).append("\n");
        sb.append("Assistants: ");
        for (Assistant assistant: assistants) {
            sb.append(assistant).append(" ");
        }
        sb.append("\n");
        for (Map.Entry<Grade, Student> studentMap: studentsGrades.entrySet()) {
            sb.append("Student: ").append(studentMap.getValue()).append(", Grade: ").append(studentMap.getKey()).append("\n");
        }

        return sb.toString();
    }

    public SortedMap<Grade, Student> getStudentGrades() {
        return studentsGrades;
    }

    public Student getBestStudent() {
        return strategy.getBestStudent(studentsGrades.keySet());
    }

    public String getName() {
        return name;
    }
}
