import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileWriterUtil {
    public static void writeToFile(String filename, List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Header
            writer.write(String.format("%-10s | %-20s | %-12s | %-5s | %n",
                    "StudentID", "Student Name", "Course Code", "Grade"));

            // Data rows
            for (Student student : students) {
                for (Course course : student.getCourses()) {
                    writer.write(String.format("%-10s | %-20s | %-12s | %5.1f | %n",
                            student.getId(),
                            student.getName(),
                            course.getCourseCode(),
                            course.calculateFinalGrade()));
                }
            }
        }
    }
}
