
// FileWriterUtil.java
import java.io.*;
import java.util.*;

public class FileWriterUtil {
    public static void writeToFile(String filename, List<Student> students) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (Student s : students) {
            for (Course c : s.getCourses()) {
                bw.write(s.getId() + "," +
                        s.getName() + "," +
                        c.getCourseCode() + "," +
                        c.calculateFinalGrade());
                bw.newLine();
            }
        }
        bw.close();
    }
}
