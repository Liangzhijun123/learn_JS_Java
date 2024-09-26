package java_begin.week_2;

import java.util.LinkedList;

public class listText {
    public static void main(String[] args) {

        // single linked list
        LinkedList<String> list = new LinkedList<String>();

        list.add("Head");
        list.add("Element 10");
        list.add("Element 20");
        list.add("Element 40");

        System.out.println("List test elements: ");
        // list.size() -> it goes through the entire list of index
        for (int i = 0; i < list.size(); i++) 
            // list.get(i), interate the value just to get the index
            System.out.println("Node value: " + list.get(i));

        //add index 2 then the index increment to fit in the index 2 between index 1 and index 3
        list.add(2,"element 11");

        System.out.println("list after insert");
        for (int i = 0; i < list.size(); i++) 
            // list.get(i), interate the value just to get the index
            System.out.println("Node value: " + list.get(i));

        // remove() it remove the head of the list -> decremement
        list.remove();

        // set index 0 as head
        list.set(0,"head");

        System.out.println("list after delete and update:");
        for (int i = 0; i < list.size(); i++) 
            // list.get(i), interate the value just to get the index
            System.out.println("Node value: " + list.get(i));



        // double linked list
        
        
    }
}
