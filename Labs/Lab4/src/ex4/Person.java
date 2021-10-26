package ex4;

import java.util.Enumeration;
import java.util.Vector;

public class Person {
    private String name, address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return name + " lives at " + address;
    }
}

class Student extends Person {
    private Vector courses, grades;

    public Student(String name, String address) {
        super(name, address);
        courses = new Vector();
        grades = new Vector();
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("\n" + getGradesWithCourses());

        return s.toString();
    }

    public void addCourseGrade(String course, int grade) {
        courses.add(course);
        grades.add(grade);
    }

    private String getGradesWithCourses() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < courses.size(); i++) {
            s.append(courses.get(i) + ": " + grades.get(i) + '\n');
        }

        return s.toString();
    }

    public void printGrades() {
        System.out.println(getGradesWithCourses());
    }

    public double getAverageGrade() {
        int sum = 0;
        for (int i = 0; i < courses.size(); i++) {
            sum += (int) courses.get(i);
        }

        return sum / courses.size();
    }
}

class Teacher extends Person {
    private Vector courses;

    public Teacher(String name, String address) {
        super(name, address);
        courses = new Vector();
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(super.toString());
        s.append("\n" + courses.toString());

        return s.toString();
    }

    public boolean addCourse(String course) {
        if (courses.contains(course)) return false;
        courses.add(course);

        return true;
    }

    public boolean removeCourse(String course) {
        if (!courses.contains(course)) return false;
        courses.remove(course);

        return true;
    }

}

class Test4 {
    public static void main(String args[]) {
        Person student, teacher, person;
        student = new Student("Popescu Ion", "Bucuresti");
        teacher = new Teacher("Ionescu Gigel", "Bucuresti");
        person = new Person("Maria", "Iasi");
        assert (person.getName().equals("Maria")) : "Metoda getName din clasa Person nu este implementata corect";
        assert (((Teacher) teacher).addCourse("Programare")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        assert (((Teacher) teacher).addCourse("Algoritmica")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        assert (((Teacher) teacher).addCourse("Matematica")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        assert (!((Teacher) teacher).addCourse("Programare")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        assert (((Teacher) teacher).removeCourse("Programare")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        assert (!((Teacher) teacher).addCourse("Programare")) : "Metoda addCourse din clasa Teacher nu este " +
                "implementata corect";
        ((Student) student).addCourseGrade("Programare", 10);
        ((Student) student).addCourseGrade("Algoritmica", 9);
        ((Student) student).addCourseGrade("Matematica", 8);
        assert (Math.abs(((Student) student).getAverageGrade() - 9.00) <= 0.001) : "Metoda getAverageGrade din clasa " +
                "Student nu a fost implementat corect";
        ((Student) student).printGrades();
        //Ce metoda toString se va apela? Din ce clasa?
        System.out.println(student);
        System.out.println(person);
        System.out.println("Felicitari! Problema a fost rezolvata corect!");
    }
}