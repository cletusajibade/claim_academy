package sets;

import java.util.HashSet;
import java.util.Set;

public class FirstSet {
    static void main(String[] args) {
        HashSet<String> topics = new HashSet<>();
        IO.println(topics.add("Java"));
        IO.println(topics.add("SQL"));
        // IO.println(topics.add("Java"));
        IO.println(topics.add("Python"));
        IO.println(topics.add("PHP"));

        IO.println(topics);

        for(String topic: topics){
            IO.println(topic);
        }

        Set<Integer> seats = new HashSet<>();
        seats.add(10);
        seats.add(20);
        seats.add(30);

        IO.println(seats);
        IO.println(seats.contains(20));
        IO.println(seats.remove(20)); //remove only takes an object not an index
        IO.println(seats);
        IO.println(seats.remove(99));

        //clear the seats
        seats.clear();
        IO.println(seats);
        // Check if the set is empty
        IO.println(seats.isEmpty());
    }
}
