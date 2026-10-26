package queues;

import java.util.ArrayDeque;
import java.util.Queue;

public class TutoringQueue {
    static void main(String[] args) {
        Queue<String> waiting = new ArrayDeque<>();
        waiting.offer("A104");
        waiting.offer("B207");
        waiting.offer("C310");

        IO.println("Peek: " + waiting.peek());
        IO.println("Size: " + waiting.size());

        IO.println("Serving: " + waiting.poll());
        waiting.offer("D415");
        IO.println("Queue content: " + waiting);

        while (!waiting.isEmpty()) {
            IO.println(waiting.poll());
        }

        IO.println("Final size: " + waiting.size());
        IO.println("isEmpty: " + waiting.isEmpty());
        IO.println("Empty peek: " + waiting.peek());
        IO.println("Empty poll: " + waiting.poll());
    }
}
