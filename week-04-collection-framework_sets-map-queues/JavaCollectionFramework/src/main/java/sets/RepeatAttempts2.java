package sets;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RepeatAttempts2 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> admitted = new HashSet<>();

        int repeatAttempts = 0;
        IO.println("Instruction: Enter 'stop' to finish");
        while (true) {
            IO.println("Enter your scan:");
            String scan = scanner.nextLine();

            if (scan.equals("stop")) {
                break;
            } else {
                if (!admitted.add(scan)) {
                    repeatAttempts++;
                    IO.println(scan + " already admitted, continue.");
                }
            }
        }
        IO.println("Repeat Attempts: " + repeatAttempts);
        IO.println("Unique attendees size: " + admitted.size());
        IO.println("Unique attendees: " + admitted);
    }
}
