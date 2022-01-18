import java.util.Collection;

public class BestTotalScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Grade maxGrade = null;
        for (Grade grade: grades) {
            if (maxGrade == null) maxGrade = grade;
            else if (maxGrade.getTotal() < grade.getTotal()) maxGrade = grade;
        }

        return maxGrade.getStudent();
    }
}
