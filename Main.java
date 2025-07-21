import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Fail-fast usage check
        String nameFile, courseFile;
        if (args.length == 2) {
            nameFile = args[0];
            courseFile = args[1];
        } else {
            // fallback: use default filenames in working directory
            nameFile = "NameFile.txt";
            courseFile = "CourseFile.txt";
            System.out.println("No args passed—using defaults: "
                    + nameFile + ", " + courseFile);
        }

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
