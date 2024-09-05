package java_begin; 

import java.util.Stack;

public class stackTest {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int index = 0; index <= 5; index++) {
            stack.push(index);
        }

        while(!stack.empty()){
            System.out.println("the current top element is: " + stack.peek());
            System.out.println("popped: " + stack.pop() + " off the stack");
        }
    }
}
