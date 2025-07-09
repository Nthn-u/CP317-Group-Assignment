
// Student.java
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<Course> courses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
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
