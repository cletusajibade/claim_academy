package maps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class LinkedHashMapDemo {
    static void main(String[] args) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("SQL", 2);
        counts.put("Java", 2);
        counts.put("HTML", 1);
        counts.put("SQL", 3);

        IO.println("LinkedHashMap: " + counts);

        Map<String, Integer> countsSorted = new TreeMap<>(counts);
        IO.println("TreeMap: " + countsSorted);

        counts.remove("SQL");
        IO.println("LinkedHashMap: " + counts);
        counts.put("SQL", 3);
        IO.println("LinkedHashMap: " + counts);

        IO.println(counts.get("Python"));
        int returnValue = counts.getOrDefault("Python",0);
        IO.println("returnValue: "+returnValue);

    }
}
