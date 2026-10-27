package maps;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapPractice {
    static void main(String[] args) {
        String[] requests = {
                "SQL", "Java", "SQL", "HTML", "Java", "SQL"
        };
        Map<String, Integer> counts = new LinkedHashMap<>();
        IO.println(counts);
        //Looping through `requests`
        for (String request : requests) {
            int previous = counts.getOrDefault(request, 0);
            counts.put(request, previous + 1);
        }
        IO.println(counts);

        int total = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            IO.println(entry.getKey() + ":" + entry.getValue());
            total += entry.getValue();
        }

        int distinct = counts.size();
        IO.println("Total: " + total);
        IO.println("Distinct topics: " + distinct);
        IO.println("Repeated attempts: " + (total - distinct));


    }
}
