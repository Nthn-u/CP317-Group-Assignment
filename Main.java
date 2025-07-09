
// Main.java
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Student> studentMap = FileReaderUtil.readNameFile("NameFile.txt");
            FileReaderUtil.readCourseFile("CourseFile.txt", studentMap);
            List<Student> sortedStudents = GradeCalculator.calculateSortedGrades(studentMap);
            FileWriterUtil.writeToFile("FinalGrades.txt", sortedStudents);
            System.out.println("Grade calculation complete. Output written to FinalGrades.txt");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}