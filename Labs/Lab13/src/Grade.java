public class Grade implements Comparable<Grade> {
    private Double partialScore, examScore;
    private Student student;
    private String course;

    public Grade(String course, Student student) {
        partialScore = 0.0;
        examScore = 0.0;
        this.course = course;
        this.student = student;
    }

    public Grade(String course, Student student,Double partialScore, Double examScore ) {
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.course = course;
        this.student = student;
    }

    public void setPartialScore(Double score) {
        partialScore = score;
    }

    public void setExamScore(Double score) {
        examScore = score;
    }

    public Student getStudent() { return student; }

    public Double getTotal() {
        return partialScore + examScore;
    }

    public Double getPartialScore() { return partialScore; }

    public Double getExamScore() { return examScore; }

    @Override
    public int compareTo(Grade o) {
        Double res = getTotal() - o.getTotal();
        if (res < 0) return -1;
        if (res > 0) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(getTotal());
    }
}
