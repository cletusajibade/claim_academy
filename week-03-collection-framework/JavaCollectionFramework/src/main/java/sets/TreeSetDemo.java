package sets;

import java.util.*;

public class TreeSetDemo {
    static void main(String[] args) {
        Set<Integer> levels = new TreeSet<>();
        levels.add(50);
        levels.add(20);
        levels.add(10);
        levels.add(30);

        IO.println(levels);

        Set<String> colors = new TreeSet<>();
        colors.add("RED");
        colors.add("Blue");
        colors.add("Cyan");
        colors.add("CYan");
        colors.add("Pink");
        colors.add("PiNk");

        IO.println(colors);

        List<String> requests = new ArrayList<>();
        requests.add("Tea");
        requests.add("Coffee");
        requests.add("Tea");
        requests.add("Juice");

        IO.println(requests);

        Set<String> unique = new LinkedHashSet<>(requests);
        IO.println(unique);

        ArrayList<String> uniqueList = new ArrayList<>(unique);
        IO.println(uniqueList);
        IO.println(uniqueList.get(1));

    }
}
