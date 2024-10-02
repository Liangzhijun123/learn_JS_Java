import java.util.HashMap;
import java.lang.reflect.Field;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class Task implements Comparable<Task>
{
   private String taskName = "";
   private int myPriority = Integer.MAX_VALUE;

   public Task(String name, int priority)
   {
      taskName = name;
      myPriority = priority;
   }

   public int compareTo(Task otherTask)
   {
      return Integer.compare(myPriority, otherTask.myPriority);
   }

   public String toString()
   {
      return "["+ taskName + ", priority=" + myPriority + "]";
   }
}

public class HashMapPQ {
   public static void priorityQueueTest() {
      PriorityQueue<Task> myPQ = new PriorityQueue<Task>();
      
      myPQ.add(new Task("Task 1", 4));
      myPQ.add(new Task("Task 2", 7));
      myPQ.add(new Task("Task 3", 1));
      myPQ.add(new Task("Task 4", 3));
      myPQ.add(new Task("Task 5", 6));
      
      while(myPQ.peek() != null)
         System.out.println(myPQ.poll());
   }

   public static int getHashMapCapacity(HashMap <Integer, String> myMap) {
      int capacity = 0;
      
      try {
         Field tableField = HashMap.class.getDeclaredField("table");
         tableField.setAccessible(true);
         Object[] table = (Object[]) tableField.get(myMap);
         capacity = table.length;
      }
      catch(Exception e) {}
      
      return capacity;
   }
   
   public static void hashMapTest() {
      // capcaity is 4. collection of books
      HashMap<Integer, String> books = new HashMap<Integer, String>(4, (float) 0.75); 
      
      Integer[] ISBN = {1503222683, 1533557128, 1540482669, 1060256656};
      String[] titles = {"Alice's Adventures in Wonderland", "Through the Looking Glass",
                         "The Rime of the Ancient Mariner", "The Giving Tree"};
      
      for(int i = 0; i < titles.length; i++) {
         books.put(ISBN[i], titles[i]);
         System.out.println("Number of books = " + books.size());
         System.out.println("HashMap capacity = " + getHashMapCapacity(books));
      }
      
      System.out.println(books); 
      System.out.println("Found ISBN 1533557128 = " + books.containsKey(1533557128));
      System.out.println("Retrieved " + books.get(1533557128) + " via ISBN 1533557128");
      System.out.println("Found The Giving Tree = " + books.containsValue("The Giving Tree"));
   
      books.replace(1533557128, "The Crucible");
      System.out.println("Retrieved " + books.get(1533557128) + " via ISBN 1533557128");
      System.out.println(books);
   }

   public static void hashMapEntries()
   {
      HashMap<Integer, String> books = new HashMap<Integer, String>(); 
     
      Integer[] ISBN = {1503222683, 1533557128, 1540482669, 1060256656};
      String[] titles = {"Alice's Adventures in Wonderland", "Through the Looking Glass",
                         "The Rime of the Ancient Mariner", "The Giving Tree"};
      
      for(int i = 0; i < titles.length; i++) {
         books.put(ISBN[i], titles[i]);
      }
      
      Set<Entry<Integer, String>> entries = books.entrySet();
      
      System.out.println("Entries in this HashMap are:");
      for(Entry<Integer, String> entry : entries)
         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
   }

   public static void main(String[] args) {
      hashMapTest();
      hashMapEntries();
      priorityQueueTest();
   }
}
