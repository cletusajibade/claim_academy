package maps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountOccurence {
    static void main(String[] args) {
        //SQL, Java, HTML
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> counts = new LinkedHashMap<>(); //[]

        IO.println("Begin registration. Enter 'stop' to end registration");
        while (true) {
            IO.print("Enter session: ");
            String session = scanner.nextLine(); //SQL

            if (session.equals("stop")) {
                break;
            } else {
                int previous = counts.getOrDefault(session, 0);
                counts.put(session, previous + 1);//put(Java,2)
                //counts={SQL=3, Java=2}
            }
        }
        IO.println(counts);

        int total = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            IO.println(entry.getKey() + "->" + entry.getValue());
            total = total + entry.getValue();
        }

        IO.println("Distinct Session:" + counts.size());
        IO.println("Total Attendance:" + total);
        IO.println("Sorted: " + new TreeMap<>(counts));
        IO.println("Orignal: " + counts);


    }
}
