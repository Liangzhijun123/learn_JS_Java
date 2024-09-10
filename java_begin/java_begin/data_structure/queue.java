package java_begin.data_structure;

import java.util.LinkedList;
import java.util.Queue;

public class queue {
    public static void main(String[] args) {
        /*
         * Queue = First in first out. A line of people. whoever is first, gets out
         * first
         * 
         * linear data structure
         * 
         * add = enqueue, offer(). add to end of tail
         * remove = dequeue, poll() - remove the first thing
         * peek()
         * isEmpty()
         * contains()
         * size()
         */

        Queue<String> queue = new LinkedList<String>();

        queue.offer("karen");
        queue.offer("chad");
        queue.offer("steve");
        queue.offer("teaky");

        System.out.println("peeking: "+queue.peek());
        // System.out.println("removing: " + queue.poll());
        // System.out.println("removing: " + queue.poll());
        // System.out.println("removing: " + queue.poll());
        System.out.println("the people in the line right now: "+queue);
        System.out.println("length of the queue: "+queue.size());
        System.out.println("is this person in the line? "+ queue.contains("teaky"));

    }
}
