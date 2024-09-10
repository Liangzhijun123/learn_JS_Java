package java_begin.data_structure;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class prior_queue {
    public static void main(String[] args) {
        /*
         * Prior queue - First in first out that server element.
         * 
         *
         */
        
        // standard queue
        Queue<Double> queue = new LinkedList<>();
        queue.offer(3.0);
        queue.offer(5.0);
        queue.offer(2.0);
        queue.offer(1.0);
        queue.offer(7.0);

        // while the queue is not empty, remove the element
        while (!queue.isEmpty()) {
            System.out.println("standard queue: "+queue.poll());
        }

        // prior queue
        Queue<Double> queue2 = new PriorityQueue<>();
        queue2.offer(3.0);
        queue2.offer(5.0);
        queue2.offer(2.0);
        queue2.offer(1.0);
        queue2.offer(7.0);

        while (!queue2.isEmpty()) {
            System.out.println("prior queue: "+queue2.poll());
        }
    }
}
