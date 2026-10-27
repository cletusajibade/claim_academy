package linkedlist;

import java.util.LinkedList;

public class LabTasks {
    static void main(String[] args) {
        IO.println("Task 1");
        {
            LinkedList<String> pets = new LinkedList<>();
            pets.add("Cat"); //[Cat]
            pets.add("Dog"); //[Cat, Dog]
            pets.addFirst("Bird"); // [Bird, Cat, Dog]
            pets.set(1, "Fish"); // [Bird, Fish, Dog]
            pets.removeLast(); // [Bird, Fish]
            System.out.println(pets); // [Bird, Fish]
            System.out.println(pets.size());// 2
        }

        IO.println("Task 2");
        {
            LinkedList<String> playlist = new LinkedList<>();
            // Step 1:
            playlist.add("Focus");
            playlist.add("Break");
            playlist.add("Review");
            IO.println(playlist);

            // Step 2:
            playlist.addFirst("Welcome");
            playlist.addLast("Goodbye");
            IO.println(playlist);

            // Step 3:
            playlist.add(3, "Questions");
            IO.println(playlist);

            // Step 4:
            playlist.set(2, "Practice");
            IO.println(playlist);

            // Step 5:
            playlist.remove("Review");
            IO.println(playlist);

            // Step 6:
            IO.println("First Item: " + playlist.getFirst());
            IO.println("Last Item: " + playlist.getLast());
            IO.println("Size: " + playlist.size()); // arrayName.length

            IO.println();

            for (String item : playlist) {
                IO.println(item);
            }

        }
    }
}
