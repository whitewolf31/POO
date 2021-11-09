package ex3;

import java.util.Collections;
import java.util.Vector;

public class Student implements Persoana {
    private String nume;
    private Vector<Double> medii;

    public Student(String nume) {
        this.nume = nume;
        this.medii = new Vector<Double>();
    }

    public String getNume() {
        return nume;
    }

    public void addMedie(double medie) {
        medii.add(medie);
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double calculMedieGenerala() {
        double sum = 0;
        for (int i = 0; i < medii.size(); i++) {
            sum += medii.get(i);
        }

        return sum / medii.size();
    }

    @Override
    public int compareTo(Object o) {
        Student otherStudent = (Student) o;

        return (calculMedieGenerala() - otherStudent.calculMedieGenerala()) > 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return getNume() + ": " + medii.toString();
    }

    public static void main(String[] args) {
        Student s1 = new Student("Laur");
        Student s2 = new Student("Tudor");
        Student s3 = new Student("Alex");

        s1.addMedie(9.5);
        s1.addMedie(9);
        s1.addMedie(10);

        s2.addMedie(8);
        s2.addMedie(10);
        s2.addMedie(9.5);

        s3.addMedie(9);
        s3.addMedie(10);
        s3.addMedie(10);

        Vector<Student> students = new Vector<Student>();
        students.add(s3);
        students.add(s1);
        students.add(s2);
        Collections.sort(students);
        System.out.println(students);
    }
}
