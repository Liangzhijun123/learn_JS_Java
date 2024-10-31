import java.util.*;

public class PE {
   public static void roundRobin(int numTasks, int numRuns) {
      Queue<Integer> q = new LinkedList<Integer>();

      // ******* Do not modify Lines 8 and 9 ********
      for (int i = 1; i <= numTasks; i++)
         q.add(i);
      // ******* Do not modify Lines 8 and 9 ********

      // ******* Add your code below this line *******

      for (int run = 1; run <= numRuns; run++) {
         int taskSize = q.size();

         for (int i = 0; i < taskSize; i++) {
            int currentTask = q.poll();
            System.out.println("Run " + run + ": Task " + currentTask);
            q.add(currentTask);
         }
      }

   }

   // Task 1
   /*
    * Using a character array to reverse a string is more efficient than using
    * string concatenation due to the immutable of strings.
    * 
    * Each time two strings are concatenated, a new string object is created,
    * which increases the time complexity to O(n^2).
    * 
    * Using a character array allows direct assignment of characters in reverse
    * order in a time complexity of O(n). the character array approach much
    * faster for large string.
    */


   // Task 2
   class Node {
      int data;
      Node left, right;

      public Node(int item) {
         data = item;
         left = right = null;
      }
   }

   Node root;

   public void insert(int data) {
      root = insertRec(root, data);
   }

   private Node insertRec(Node root, int data) {
      if (root == null) {
         root = new Node(data);
         return root;
      }

      if (data < root.data)
         root.left = insertRec(root.left, data);
      else if (data > root.data)
         root.right = insertRec(root.right, data);
      return root;
   }

   public boolean search(int data) {
      return searchMethod(root, data);
   }

   private boolean searchMethod(Node root, int data) {
      if (root == null)
         return false;
      if (root.data == data)
         return true;
      return data < root.data ? searchMethod(root.left, data) : searchMethod(root.right, data);
   }

   public void inOrder() {
      inOrderMethod(root);
      System.out.println();
   }

   private void inOrderMethod(Node root) {
      if (root != null) {
         inOrderMethod(root.left);
         System.out.print(root.data + " ");
         inOrderMethod(root.right);
      }
   }

   public void preOrder() {
      preOrderMethod(root);
      System.out.println();
   }

   private void preOrderMethod(Node root) {
      if (root != null) {
         System.out.print(root.data + " ");
         preOrderMethod(root.left);
         preOrderMethod(root.right);
      }
   }

   // Task 5
   class NodeLinked {
      int data;
      NodeLinked next;

      public NodeLinked(int data) {
         this.data = data;
         this.next = null;
      }
   }

   NodeLinked head;

   public void insertAt(int data, int position) {
      NodeLinked newNode = new NodeLinked(data);

      if (position == 0) {
         
         newNode.next = head;
         head = newNode;
      } else {
         NodeLinked current = head;
         NodeLinked previous = null;
         int currentIndex = 0;

        
         while (current != null && currentIndex < position) {
            previous = current;
            current = current.next;
            currentIndex++;
         }

         
         newNode.next = current;
         if (previous != null) {
            previous.next = newNode;
         }
      }
   }

   public void updateAt(int position, int newValue) {
      if (head == null) {
         System.out.println("List is empty");
         return;
      }

      NodeLinked current = head;
      int currentIndex = 0;

      while (current != null && currentIndex < position) {
         current = current.next;
         currentIndex++;
      }

      if (current != null) {
         current.data = newValue;
      } else {
         System.out.println("Position out of bounds");
      }
   }

   public void delete(int data) {
      if (head == null) {
         System.out.println("List is empty");
         return;
      }

      if (head.data == data) {
         head = head.next;
         return;
      }

      NodeLinked current = head;
      NodeLinked previous = null;

      while (current != null && current.data != data) {
         previous = current;
         current = current.next;
      }

      if (current == null) {
         System.out.println("Element not found");
         return;
      }

      previous.next = current.next;
   }

   public void display() {
      if (head == null) {
         System.out.println("List is empty");
         return;
      }

      NodeLinked current = head;
      while (current != null) {
         System.out.print(current.data + " -> ");
         current = current.next;
      }
      System.out.println("null");
   }

   // Task 6
   /*
    * Initial Capacity:
    * 
    * - The initial capacity refers to the size of the underlying array when the
    * HashMap is created. It defines how many things the hash table starts with.
    * - The default initial capacity in Java’s HashMap is 16.
    * - If the number of elements exceeds the current capacity, the hash table will
    * be resized (doubled) to accommodate more entries, but this resizing operation
    * is costly (rebuilding the table and rehashing the keys).
    * 
    * Issue with setting the inital capacity too low or high:
    * 
    * - When the number of elements exceeds the product of capacity and load
    * factor, the HashMap resizes. with an initial capacity of 16 and
    * a load factor of 0.75, once 12 elements are inserted, the capacity will be
    * doubled to 32.
    * - During resizing, all the existing key-value pairs need to be rehashed to
    * new hash
    */

   // Task 7
   /*
    * Binary Search Tree (BST):
    * 
    * - A binary search tree :
    * - The value of every node in the left subtree is less than the value of the
    * root.
    * - The value of every node in the right subtree is greater than the value of
    * the root.
    * - Rule: Left < Root < Right.
    * - Used for efficient searching, insertion, and deletion (average time
    * complexity: O(log n)).
    * 
    * Heap:
    * 
    * - A heap is used to implement priority queues. It has
    * two types:
    * - Max Heap: The value of the parent node is greater than or equal to the
    * values of its children.
    * - Min Heap: The value of the parent node is less than or equal to the values
    * of its children.
    * - Rule: Parent ≥ Children (Max Heap) or Parent ≤ Children (Min Heap).
    * - Used in algorithms like Dijkstra’s shortest path, heapsort, and priority
    * queues.
    */

   public static void main(String[] args) {
      System.out.println("Programming Exercise: ");
      roundRobin(5, 5);

      System.out.println("Task 1: ");
      System.out.println(" Using a character array to reverse a string is more efficient than using\r\n" + //
                  "    * string concatenation due to the immutable of strings.\r\n" + //
                  "    * \r\n" + //
                  "    * Each time two strings are concatenated, a new string object is created,\r\n" + //
                  "    * which increases the time complexity to O(n^2).\r\n" + //
                  "    * \r\n" + //
                  "    * Using a character array allows direct assignment of characters in reverse\r\n" + //
                  "    * order in a time complexity of O(n). the character array approach much\r\n" + //
                  "    * faster for large string.");
      
      System.out.println("Task 2: ");
      // Task 2
      PE bst = new PE();

      bst.insert(7);
      bst.insert(4);
      bst.insert(12);
      bst.insert(2);
      bst.insert(6);
      bst.insert(9);
      bst.insert(19);
      bst.insert(3);
      bst.insert(5);
      bst.insert(8);
      bst.insert(11);
      bst.insert(15);
      bst.insert(20);

      System.out.println("Search 40: " + bst.search(11));

      System.out.print("In-order traversal: ");
      bst.inOrder();

      System.out.print("Pre-order traversal: ");
      bst.preOrder();

      System.out.println("Task 5: ");
      // Task 5
      PE linkedlist = new PE();
      linkedlist.insertAt(1, 0);
      linkedlist.insertAt(2, 1);
      linkedlist.insertAt(3, 2);
      linkedlist.insertAt(4, 3);
      linkedlist.insertAt(5, 4);
      linkedlist.insertAt(6, 5);

      linkedlist.delete(2);
      System.out.println("Display after deleted element 2: ");
      linkedlist.display();

      linkedlist.updateAt(2, 23);
      System.out.println("Update value 2 to 23: ");
      linkedlist.display();

      linkedlist.insertAt(99, 4);
      System.out.println("Insert 99 at Element 4: ");
      linkedlist.display();

      linkedlist.insertAt(65, 0);
      System.out.println("Insert 65 at Element 0: ");
      linkedlist.display();

      linkedlist.updateAt(6, 88);
      System.out.println("Update value 6 to 88: ");
      linkedlist.display();

      System.out.println("Task 6: ");
      System.out.println("Initial Capacity:\r\n" + //
                  "    * \r\n" + //
                  "    * - The initial capacity refers to the size of the underlying array when the\r\n" + //
                  "    * HashMap is created. It defines how many things the hash table starts with.\r\n" + //
                  "    * - The default initial capacity in Java’s HashMap is 16.\r\n" + //
                  "    * - If the number of elements exceeds the current capacity, the hash table will\r\n" + //
                  "    * be resized (doubled) to accommodate more entries, but this resizing operation\r\n" + //
                  "    * is costly (rebuilding the table and rehashing the keys).\r\n" + //
                  "    * \r\n" + //
                  "    * Issue with setting the inital capacity too low or high:\r\n" + //
                  "    * \r\n" + //
                  "    * - When the number of elements exceeds the product of capacity and load\r\n" + //
                  "    * factor, the HashMap resizes. with an initial capacity of 16 and\r\n" + //
                  "    * a load factor of 0.75, once 12 elements are inserted, the capacity will be\r\n" + //
                  "    * doubled to 32.\r\n" + //
                  "    * - During resizing, all the existing key-value pairs need to be rehashed to\r\n" + //
                  "    * new hash");

      System.out.println("Task 7: ");
      System.out.println("Binary Search Tree (BST):\r\n" + //
                  "    * \r\n" + //
                  "    * - A binary search tree :\r\n" + //
                  "    * - The value of every node in the left subtree is less than the value of the\r\n" + //
                  "    * root.\r\n" + //
                  "    * - The value of every node in the right subtree is greater than the value of\r\n" + //
                  "    * the root.\r\n" + //
                  "    * - Rule: Left < Root < Right.\r\n" + //
                  "    * - Used for efficient searching, insertion, and deletion (average time\r\n" + //
                  "    * complexity: O(log n)).\r\n" + //
                  "    * \r\n" + //
                  "    * Heap:\r\n" + //
                  "    * \r\n" + //
                  "    * - A heap is used to implement priority queues. It has\r\n" + //
                  "    * two types:\r\n" + //
                  "    * - Max Heap: The value of the parent node is greater than or equal to the\r\n" + //
                  "    * values of its children.\r\n" + //
                  "    * - Min Heap: The value of the parent node is less than or equal to the values\r\n" + //
                  "    * of its children.\r\n" + //
                  "    * - Rule: Parent ≥ Children (Max Heap) or Parent ≤ Children (Min Heap).\r\n" + //
                  "    * - Used in algorithms like Dijkstra’s shortest path, heapsort, and priority\r\n" + //
                  "    * queues.");
   }
}
