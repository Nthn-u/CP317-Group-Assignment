
// GradeCalculator.java
import java.util.*;

public class GradeCalculator {
    public static List<Student> calculateSortedGrades(Map<String, Student> studentMap) {
        List<Student> studentList = new ArrayList<>(studentMap.values());
        studentList.sort(Comparator.comparing(Student::getStudentId));
        return studentList;
    }
}
