import java.util.ArrayList;
import java.util.List;

public class Catalog implements Subject{
    private static Catalog instance = null;

    private List<Course> courses;

    private List<Observer> observers;

    private Catalog() {
        courses = new ArrayList<Course>();
        observers = new ArrayList<Observer>();
    }

    public static Catalog getInstance() {
        if (instance == null) instance = new Catalog();

        return instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Course course: courses) {
            sb.append(course);
        }

        return sb.toString();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for (Observer observer: observers) {
            if (grade.getStudent().isParent((Parent) observer)) {
                observer.update(new Notification(grade.getTotal()));
            }
        }
    }

    public Course getCourse(String name) {
        for (Course course: courses) {
            if (course.getName().equals(name)) return course;
        }

        return null;
    }
}
