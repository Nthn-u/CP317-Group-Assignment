
// FileWriter.java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileWriterUtil {
    public static void writeToFile(String filename, List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                for (Course course : student.getCourses()) {
                    writer.write(String.format("%s, %s, %s, %.1f\n",
                            student.getStudentId(),
                            student.getName(),
                            course.getCourseCode(),
                            course.calculateFinalGrade()));
                }
            }
        }
    }
}