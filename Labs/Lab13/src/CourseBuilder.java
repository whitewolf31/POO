import java.util.*;

public class CourseBuilder {
    public String name;

    public Teacher titulary;

    public List<Assistant> assistants;

    public SortedMap<Grade, Student> studentsGrades;

    public Strategy strategy;

    public CourseBuilder(String name) {
        this.name = name;
    }

    public CourseBuilder teacher(Teacher t) {
        titulary = t;
        return this;
    }

    public CourseBuilder assistant(Assistant assistant) {
        if (assistants == null) assistants = new ArrayList<Assistant>();
        assistants.add(assistant);
        return this;
    }

    public CourseBuilder grade(Grade grade) {
        if (studentsGrades == null) studentsGrades = new TreeMap<Grade, Student>();
        studentsGrades.put(grade, grade.getStudent());
        return this;
    }

    public CourseBuilder strategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public Course build() {
        return new Course(this);
    }
}
