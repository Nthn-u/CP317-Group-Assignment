import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private List<Course> courses;

    public Student(String id, String name) {
        super(id, name);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean hasCourse(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                return true;
            }
        }
        return false;
    }
}