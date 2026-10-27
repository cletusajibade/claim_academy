package maps;

import java.util.HashMap;
import java.util.Map;

public class StudentDirectoryLab {
    static void main(String[] args) {
        // Step 1
        Map<String, String> students = new HashMap<>();
        students.put("A104", "Ava");
        students.put("B207", "Ben");
        students.put("C310", "Ava");
        IO.println(students);
        IO.println(students.size());

        // Step 2
        IO.println(students.get("B207"));

        // Step 3
        String value = students.put("B207", "Benjamin");
        IO.println(value);

    }
}
