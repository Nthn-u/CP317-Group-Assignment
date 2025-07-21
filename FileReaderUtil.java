import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileReaderUtil {

    public static Map<String, Student> readNameFile(String filename) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("Name file path is blank");
        }
        Map<String, Student> studentMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " malformed in name file (expected 2 columns): " + line);
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                if (!isValidStudentId(id)) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " has invalid student ID (must be 9 digits): " + id);
                }

                if (studentMap.containsKey(id)) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " duplicate student ID in name file: " + id);
                }

                studentMap.put(id, new Student(id, name));
            }
        }

        return studentMap;
    }

    public static void readCourseFile(String filename, Map<String, Student> studentMap) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("Course file path is blank");
        }
        if (studentMap == null) {
            throw new IllegalArgumentException("Student map is null");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " malformed in course file (expected 6 columns): " + line);
                }

                String id = parts[0].trim();
                if (!isValidStudentId(id)) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " has invalid student ID in course file: " + id);
                }

                Student student = studentMap.get(id);
                if (student == null) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " references unknown student ID: " + id);
                }

                String courseCode = parts[1].trim();
                double t1, t2, t3, fe;
                try {
                    t1 = Double.parseDouble(parts[2].trim());
                    t2 = Double.parseDouble(parts[3].trim());
                    t3 = Double.parseDouble(parts[4].trim());
                    fe = Double.parseDouble(parts[5].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " has non-numeric grade(s): " + line);
                }

                if (t1 < 0 || t1 > 100) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " test1 out of range (0–100): " + t1);
                }
                if (t2 < 0 || t2 > 100) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " test2 out of range (0–100): " + t2);
                }
                if (t3 < 0 || t3 > 100) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " test3 out of range (0–100): " + t3);
                }
                if (fe < 0 || fe > 100) {
                    throw new IllegalArgumentException(
                            "Line " + lineNum + " finalExam out of range (0–100): " + fe);
                }

                // offensive duplicate-course check without altering Student.java
                for (Course existing : student.getCourses()) {
                    if (existing.getCourseCode().equals(courseCode)) {
                        throw new IllegalArgumentException(
                                "Line " + lineNum + " duplicate course for student " + id + ": " + courseCode);
                    }
                }

                student.addCourse(new Course(courseCode, t1, t2, t3, fe));
            }
        }
    }

    private static boolean isValidStudentId(String id) {
        return id != null && id.matches("\\d{9}");
    }
}
