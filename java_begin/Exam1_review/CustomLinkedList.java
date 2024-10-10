package Exam1_review;

public class CustomLinkedList {
    class Node {
        int data; // stores the data
        Node next; // stores the reference to the next node

        public Node(int data) {
            this.data = data;  // set the node data
            this.next = null;  // default next node is null
        }
    }

    Node head; // head of the list

    // Insert a new node at the end of the list
    public void insert(int data) {
        Node newNode = new Node(data); // create a new node
        if (head == null) {
            head = newNode; // if the list is empty, the new node becomes the head
        } else {
            Node current = head;
            while (current.next != null) { // traverse to the end of the list
                current = current.next;
            }
            current.next = newNode; // link the last node to the new node
        }
    }

    // Delete the first occurrence of the given value
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head.data == data) { // if the node to be deleted is the head
            head = head.next; // move the head to the next node
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current.data != data) { // traverse to find the node
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Element not found");
            return;
        }

        previous.next = current.next; // bypass the node to delete it
    }

    // Print elements of the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList(); // Use your custom linked list class

        // Insert elements into the linked list
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        // Display the linked list
        System.out.print("Linked List: ");
        list.display(); // Output: 10 -> 20 -> 30 -> 40 -> null

        // Delete an element from the linked list
        list.delete(30);

        // Display the linked list after deletion
        System.out.print("Linked List after deletion: ");
        list.display(); // Output: 10 -> 20 -> 40 -> null

        // Try to delete an element that doesn't exist
        list.delete(50); // Output: Element not found.
    }
}
