package java_begin.data_structure;

import java.util.Stack;

public class stackk {
    public static void main(String[] args){
        /*
         * Stack - LIFO last in first out
         *  stores object in vertical tower
         * push() to add object to top of stack
         * pop() to remove from the top 
         * Peep()
         * search()
         * empty()
         * peek()
         */

        //  declare stack and intitaliate
        //  stack of string, storing strings. the object are string
        Stack<String> stack = new Stack<String>();
        
        // passing in the object "minecraft"
        stack.push("Minecraft"); //bottom
        stack.push("teaky");
        stack.push("fiverr");
        stack.push("n2"); //top

        // pop an item
        stack.pop();
        stack.pop();

        // a loop to keep adding a object
        for (int i = 0; i < 10; i++) {
            stack.push("fallout7");
        }

        // pop then assign to something else
        String myFavGame = stack.pop();

        System.out.println(myFavGame);
        System.out.println(stack);
        System.out.println(stack.search("n2"));

        

    }
}
