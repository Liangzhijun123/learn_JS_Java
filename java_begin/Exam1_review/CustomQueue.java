package Exam1_review;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {
     public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        // Enqueue elements to queue
        queue.add(10);
        queue.add(20);
        queue.add(30);
        System.out.println("Queue after enqueues: " + queue);

        // Peek front element
        System.out.println("Front element: " + queue.peek());

        // Dequeue elements from queue
        queue.poll();
        System.out.println("Queue after dequeue: " + queue);

        // Check if queue is empty
        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
