import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileReaderUtil {

    public static Map<String, Student> readNameFile(String filename) throws IOException {
        Map<String, Student> studentMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    System.err.println("Invalid name line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                if (!isValidStudentId(id)) {
                    System.err.println("Invalid student ID format in name file: " + id);
                    continue;
                }

                studentMap.put(id, new Student(id, name));
            }
        }

        return studentMap;
    }

    public static void readCourseFile(String filename, Map<String, Student> studentMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    System.err.println("Invalid course line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                if (!isValidStudentId(id)) {
                    System.err.println("Invalid student ID format in course file: " + id);
                    continue;
                }

                if (!studentMap.containsKey(id)) {
                    System.err.println("Student ID not found in NameFile: " + id);
                    continue;
                }

                try {
                    String courseCode = parts[1].trim();
                    double test1 = Double.parseDouble(parts[2].trim());
                    double test2 = Double.parseDouble(parts[3].trim());
                    double test3 = Double.parseDouble(parts[4].trim());
                    double finalExam = Double.parseDouble(parts[5].trim());

                    Course course = new Course(courseCode, test1, test2, test3, finalExam);
                    studentMap.get(id).addCourse(course);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid grade value in line: " + line);
                }
            }
        }
    }

    private static boolean isValidStudentId(String id) {
        return id.matches("\\d{9}"); // Exactly 9 digits
    }
}
