package sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetsDemo {
    static void main(String[] args) {
        ArrayList<String> attempts = new ArrayList<>();
        attempts.add("A104");
        attempts.add("B207");
        attempts.add("A104");
        attempts.add("C310");
        IO.println(attempts);

        Set<String> registered = new HashSet<>(attempts);

        IO.println("ArrayList Size: "+attempts.size());

        IO.println(registered);
        IO.println("Set Size: "+registered.size());

        //"is-a" relationship
        Set<String> registered2 = new HashSet<>();
        registered2.add("A104");// Set sees this as a duplicate
        registered2.add("B207");
        registered2.add("A104"); // Set sees this as a duplicate
        registered2.add("C310");
        IO.println(registered2);

        IO.println(registered2.contains("B207"));
    }
}
