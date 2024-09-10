package java_begin;

import java.util.LinkedList;
import java.util.Queue;

public class queueText {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i <= 5; i++) {
            q.add(i);
        }

        while (q.peek() != null) {
            System.out.println("the current head element is: " + q.peek());
            System.out.println("removed: " + q.remove() + " from the queue");

        }

    }
}
