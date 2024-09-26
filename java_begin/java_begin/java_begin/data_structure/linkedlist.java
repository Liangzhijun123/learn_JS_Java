package java_begin.data_structure;

import java.util.LinkedList;

public class linkedlist {
    public static void main(String[] args){
        /*
         * Linkedlist - store Nodes in 2 parts (data + address)
         * 
         * Single linked list
         *      Node                Node                Node
         * [data | address] -> [data | address] -> [data | address]
         * 
         * Double linked list
         *            Node                           Node                        
         * [address | data | address] <-> [address | data | address] 
         */
        LinkedList<String> linkedlist = new LinkedList<String>();

        // linked list as a queue
        linkedlist.offer("A");
        linkedlist.offer("B");
        linkedlist.offer("C");
        linkedlist.offer("D");
        linkedlist.offer("F");
        linkedlist.poll(); //remove the first thing in the list
        
        // single linked list '4' is a data. 'E' is address
        linkedlist.add(4, "E");
        linkedlist.remove("F");
        System.out.println("the index of this letter is: "+linkedlist.indexOf("F"));
        System.out.println("peek the first letter: "+linkedlist.peekFirst());
        linkedlist.addFirst("0");
        linkedlist.addLast("9");

        System.out.println(linkedlist);
    }
}
