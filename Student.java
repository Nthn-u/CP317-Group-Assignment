
// Student.java
import java.util.*;

public class Student extends Person {
    private List<Course> courses = new ArrayList<>();

    public Student(String id, String name) {
        super(id, name);
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
