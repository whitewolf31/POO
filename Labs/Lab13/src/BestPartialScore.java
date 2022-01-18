import java.util.Collection;

public class BestPartialScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Grade maxGrade = null;
        for (Grade grade: grades) {
            if (maxGrade == null) maxGrade = grade;
            else if (maxGrade.getPartialScore() < grade.getPartialScore()) maxGrade = grade;
         }

        return maxGrade.getStudent();
    }
}
