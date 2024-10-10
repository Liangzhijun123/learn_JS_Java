package Exam1_review;
import java.util.Stack;

public class CustomStack {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();

        // push element to stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("stack after pushes " + stack);

        // peek top element
        System.out.println("Top element " + stack.peek());

        // pop element from stack
        stack.pop();
        System.out.println("stack after pop: " + stack);

        // check if stack is empty
        System.out.println("is stack empty? " + stack.isEmpty());

    }
}
