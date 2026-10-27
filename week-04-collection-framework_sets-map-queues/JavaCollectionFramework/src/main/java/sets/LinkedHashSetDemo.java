package sets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<String> stops = new LinkedHashSet<>();
//        Set<String> stops = new HashSet<>();
        stops.add("Museum");
        stops.add("Cafe");
        stops.add("Library");
        stops.add("Cafe");

        IO.println(stops);

        IO.println(stops.remove("Cafe"));

        IO.println(stops);

        stops.add("Cafe");

        IO.println(stops);
    }
}
