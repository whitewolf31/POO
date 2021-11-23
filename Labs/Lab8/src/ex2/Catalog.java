package ex2;

import java.util.Comparator;
import java.util.TreeSet;

public class Catalog extends TreeSet<Catalog.Student> {

    public Catalog(Comparator comparator) {
        super(comparator);
    }

    public Catalog() {
        this(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void addStudent(String name, double media, int clazz) {
        Student studentToAdd = new Student(name, media, clazz);
        add(studentToAdd);
    }

    public Student getStudent(String name) {
        for (Student student: this) {
            if (student.name.equals(name)) return student;
        }

        return null;
    }

    public void removeStudent(String name) {
        Student foundStudent = null;
        for (Student student: this) {
            if (student.name.equals(name)) foundStudent = student;
        }

        if (foundStudent != null) remove(foundStudent);
    }

    public Catalog byClass(int clazz) {
        Catalog filtered = new Catalog(comparator());
        for (Student student: this) {
            if (student.clazz == clazz) filtered.add(student);
        }

        return filtered;
    }

    class Student implements Comparable<Student> {
        public String name;
        public Double media;
        public Integer clazz;

        public Student(String name, double media, int clazz) {
            this.name = name;
            this.media = media;
            this.clazz = clazz;
        }

        @Override
        public int compareTo(Student o) {
            if (media.equals(o.media)) {
                return name.compareTo(o.name);
            }

            return media.compareTo(o.media);
        }

        @Override
        public String toString() {
            return "Clasa " + clazz + ", Nume: " + name + ", media: " + media;
        }
    }

}
