public class Student extends User{
    private Parent father;
    private Parent mother;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void setFather(Parent parent) {
        father = parent;
    }

    public void setMother(Parent parent) {
        mother = parent;
    }

    public boolean isParent(Parent parent) {
        return father.equals(parent) || mother.equals(parent);
    }
}
