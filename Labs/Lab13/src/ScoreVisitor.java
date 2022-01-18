import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

class Pair<K, V1, V2> {
    private K key;
    private V1 value1;
    private V2 value2;
    public Pair(K key, V1 value1, V2 value2) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }
    public K getKey() {
        return key;
    }
    public V1 getValue1() {
        return value1;
    }
    public V2 getValue2() {
        return value2;
    }
}
public class ScoreVisitor implements Visitor {
    private HashMap<Teacher, ArrayList<Pair<Student, String, Double>>> examScores;
    private HashMap<Assistant, ArrayList<Pair<Student, String, Double>>> partialScores;
    public ScoreVisitor(HashMap<Teacher, ArrayList<Pair<Student, String, Double>>> examScores, HashMap<Assistant, ArrayList<Pair<Student, String, Double>>> partialScores){
        this.examScores=examScores;
        this.partialScores=partialScores;
    }
    public void visit(Assistant assistant) {
        ArrayList<Pair<Student, String, Double>> assistantScoresToAdd = partialScores.get(assistant);
        for (Pair<Student, String, Double> pair: assistantScoresToAdd) {
            Course currentCourse = Catalog.getInstance().getCourse(pair.getValue1());
            SortedMap<Grade, Student> studentGrades = currentCourse.getStudentGrades();
            boolean addedGrade = false;
            for (Map.Entry<Grade, Student> studentGrade: studentGrades.entrySet()) {
                if (studentGrade.getValue().equals(pair.getKey())) {
                    studentGrade.getKey().setPartialScore(pair.getValue2());
                    addedGrade = true;
                }
            }
            if (!addedGrade) {
                currentCourse.addGrade(pair.getKey(), new Grade(pair.getValue1(), pair.getKey(), pair.getValue2(), 0d));
            }
        }

    }
    public void visit(Teacher teacher) {
        ArrayList<Pair<Student, String, Double>> teacherScoresToAdd = examScores.get(teacher);
        for (Pair<Student, String, Double> pair: teacherScoresToAdd) {
            Course currentCourse = Catalog.getInstance().getCourse(pair.getValue1());
            SortedMap<Grade, Student> studentGrades = currentCourse.getStudentGrades();
            boolean addedGrade = false;
            for (Map.Entry<Grade, Student> studentGrade: studentGrades.entrySet()) {
                if (studentGrade.getValue().equals(pair.getKey())) {
                    studentGrade.getKey().setPartialScore(pair.getValue2());
                    addedGrade = true;
                }
            }
            if (!addedGrade) {
                currentCourse.addGrade(pair.getKey(), new Grade(pair.getValue1(), pair.getKey(), 0d,  pair.getValue2()));
            }
        }
    }
}