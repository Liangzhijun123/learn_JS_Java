package java_begin.week_2;

import java.util.LinkedList;
import java.util.ListIterator;

public class listIteratorTest {
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        list.add("Head");
        list.add("Element 10");
        list.add("Element 20");
        list.add("Element 40");

        // Instantiating a List Iterator
        ListIterator<String> listIter = list.listIterator();

        System.out.println("List iterator text list elements:");
        // Print elements of the list
        while(listIter.hasNext()) {
            System.out.println("Node value: " + listIter.next());
        }

        // Create an iterator with the cursor between element 1 and 2
        listIter = list.listIterator(2);
        listIter.add("Element 11"); 

        // Get a new iterator that starts at the beginning of the list
        listIter = list.listIterator();

        System.out.println("List after insert:");
        // Print elements of the list after insertion
        while(listIter.hasNext()) {
            System.out.println("Node value = " + listIter.next());
        }
    }
}
