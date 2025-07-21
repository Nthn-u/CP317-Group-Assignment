import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Fail-fast usage check
        if (args.length != 2) {
            System.err.println("Usage: java Main <NameFile> <CourseFile>");
            System.exit(1);
        }

        String nameFile = args[0];
        String courseFile = args[1];

        try {
            Map<String, Student> students = FileReaderUtil.readNameFile(nameFile);
            FileReaderUtil.readCourseFile(courseFile, students);
            List<Student> sorted = GradeCalculator.calculateSortedGrades(students);
            FileWriterUtil.writeToFile("FinalGrades.txt", sorted);
            System.out.println("Done. Output in FinalGrades.txt");
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
            System.exit(1);
        }
    }
}
