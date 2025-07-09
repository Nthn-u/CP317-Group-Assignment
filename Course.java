// Course.java
public class Course {
    private String courseCode;
    private double test1, test2, test3, finalExam;

    public Course(String courseCode, double test1, double test2, double test3, double finalExam) {
        this.courseCode = courseCode;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.finalExam = finalExam;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double calculateFinalGrade() {
        return Math.round((test1 * 0.2 + test2 * 0.2 + test3 * 0.2 + finalExam * 0.4) * 10.0) / 10.0;
    }
}
