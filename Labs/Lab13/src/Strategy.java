import java.util.Collection;

public interface Strategy {
    public Student getBestStudent(Collection<Grade> grades);
}
