package java_begin.week_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class reverseString {
    public static void main(String[] args) {

        String original = "teaky is the best boy ever";

        Queue<Character> queue = new LinkedList<>(); //FIFO

        Stack<Character> stack = new Stack<>(); //LIFO

        // adding the string into the queue by characterAt
        for (int i = 0; i < original.length(); i++) {
            queue.add(original.charAt(i));
        }

        // while the queue is not empty, remove the queue to the stack poll()
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        // convert the characters into string and fetch it to the reseverse string
        StringBuilder reversedString = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedString.append(stack.pop());
        }

        System.out.println("Reversed String: " + reversedString.toString());
    }
}
