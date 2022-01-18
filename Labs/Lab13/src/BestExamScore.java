import java.util.Collection;

public class BestExamScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Grade maxGrade = null;
        for (Grade grade: grades) {
            if (maxGrade == null) maxGrade = grade;
            else if (maxGrade.getExamScore() < grade.getExamScore()) maxGrade = grade;
        }

        return maxGrade.getStudent();
    }
}
