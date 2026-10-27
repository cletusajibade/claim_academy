package sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RepeatAttempts {
    static void main(String[] args) {
        String[] scans = {"A104","B207", "A104", "C310", "A104"};
        IO.println(Arrays.toString(scans));

        Set<String> admitted = new HashSet<>();
        int repeatAttempts = 0;

        for (String id: scans){//iter 5, id=A104, repeatAttempts=1
            if(!admitted.add(id)){
                repeatAttempts++; //repeatAttempts = 2
                IO.println("Already admitted: "+id);
            }
        }
        IO.println("Repeat Attempts: "+repeatAttempts);
        IO.println("Unique attendees size: "+admitted.size());
        IO.println("Unique attendees: "+admitted);
    }
}
