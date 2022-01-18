public class Notification {
    private double grade;

    public Notification(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Your son received a grade: " + grade;
    }
}
