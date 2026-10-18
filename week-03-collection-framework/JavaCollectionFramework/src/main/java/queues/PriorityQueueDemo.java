package queues;

import java.util.*;

public class PriorityQueueDemo {
    static void main(String[] args) {
        Queue<Integer> priorities = new PriorityQueue<>(Collections.reverseOrder());

        int[] input = {4, 1, 3, 1, 2};
        for (int priority: input){
            priorities.offer(priority);
        }

        IO.println("input: "+ Arrays.toString(input));
        IO.println("Next: "+ priorities.peek());
        IO.println("Size: "+ priorities.size());

        while(!priorities.isEmpty()){
            IO.println("Process: "+ priorities.poll());
        }
        IO.println("Empty poll: "+priorities.poll());
    }
}
