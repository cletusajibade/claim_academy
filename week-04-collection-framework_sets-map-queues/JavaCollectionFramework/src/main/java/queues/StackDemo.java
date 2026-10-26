package queues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class StackDemo {
    static void main(String[] args) {
        // A stack is a Last-In First-Out data structure
        //"is-a" relationship
        Deque<String> stack = new ArrayDeque<>();
        stack.push("Banana");
        stack.push("Mango");
        IO.println("Original: " + stack);
        IO.println(stack.peek()); // checking the last item but not removing it.
        IO.println(stack.pop()); // removing the last item of the stack
        IO.println("Final: " + stack);

        Queue<Integer> numbers = new ArrayDeque<>();
        numbers.offer(4);//[4]
        numbers.offer(2);//[4,2]
//        numbers.poll(); //remove 4, [2]
//        numbers.poll(); //2 removed, []
//        int value = numbers.poll(); //FIFO, removes the current head
//        System.out.println(value * 2);
        Integer next = numbers.poll();
        if (next == null) {
            System.out.println("No number waiting");
        } else {
            int value = next;
            System.out.println(value);
        }

        Queue<Integer> numbers2 = new ArrayDeque<>();
        numbers2.offer(20);
        numbers2.offer(10);
        numbers2.offer(30);

        numbers2.remove(10); //10 is a value in the queue not an index
        numbers2.remove(20);



    }
}
