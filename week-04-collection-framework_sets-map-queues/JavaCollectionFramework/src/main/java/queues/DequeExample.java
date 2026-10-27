package queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    static void main(String[] args) {
        Deque<String> line = new ArrayDeque<>();
        line.offerLast("A104");
        line.offerFirst("R001");
        line.offerLast("B207");
        IO.println(line);
        IO.println(line.peekFirst());
        IO.println(line.peekLast());
        IO.println(line.pollLast());
        IO.println(line);
        IO.println(line.pollLast());
        IO.println(line);


    }
}
