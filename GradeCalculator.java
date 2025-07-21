
// GradeCalculator.java
import java.util.*;

public class GradeCalculator {
    public static List<Student> calculateSortedGrades(Map<String, Student> studentMap) {
        List<Student> list = new ArrayList<>(studentMap.values());
        list.sort(Comparator.comparing(Student::getId));
        return list;
    }
}
