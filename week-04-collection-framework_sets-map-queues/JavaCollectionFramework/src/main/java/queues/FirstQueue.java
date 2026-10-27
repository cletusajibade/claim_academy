package queues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FirstQueue {
    static void main(String[] args) {
        //ArrayDeque can function as both a queue and a deque depending on how it's implemented
        List<String> codes = new ArrayList<>();
        codes.add("E406");
        codes.add("F376");
        Queue<String> waiting = new ArrayDeque<>(codes);
        IO.println(waiting.offer("A104")); //Similar to the `add()` method
        waiting.offer("B207");
        waiting.offer("C310");
        waiting.add("D105");
        IO.println(waiting);
        IO.println("Next: " + waiting.peek()); // The head or front of the queue
        IO.println("Size: " + waiting.size()); // The head or front of the queue

        String currentHeadOfQueue = waiting.poll();
        IO.println(waiting);

        while (!waiting.isEmpty()) {
            IO.println("Serving: " + waiting.poll()); //poll() removes the first person in line
        }
        IO.println("Queue fully processed: " + waiting); //[]
        IO.println(waiting.peek());
        IO.println(waiting.poll());


    }
}
