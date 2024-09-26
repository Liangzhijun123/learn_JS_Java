public class Recursion {
   public static int count = 0;
   public static int time = 0;

   public static int factorialRecursiveTrace(int n) {
      int value = 0;
      count++;
      time++;
      
      System.out.println("time = " + time);
      System.out.println("Beginning method call " + count);
      
      if(n == 1) { // base case n! is 1
         time++;
         System.out.println("time = " + time);
         System.out.println("returning 1! from method call " + count--);
         return 1;
      }
      else if(n == 0) { // base case 0! is 1
         time++;
         System.out.println("time = " + time);
         System.out.println("returning 0! from method call " + count--);
         return 1;
      }
      else { 
         System.out.println("method calling itself with " + (n - 1));
         // reduced problem is (n - 1)!
         // general problem is n * (n - 1)!
         value = n * factorialRecursiveTrace(n - 1);
         time++;
         System.out.println("time = " + time);
         System.out.println("returning " + value + " from method call " + count--);
         return value;
      }
   }

   public static int factorialIterative(int n) {
      int factorial = 1;
     
      if(n > 1) 
         for(int i = 2; i <= n; i++)
            factorial = factorial * i;
     
      return factorial;
   }
   
   public static int factorialRecursive(int n) {
      if(n == 1) // base case n! is 1
         return 1;
      else if(n == 0) // base case 0! is 1
         return 1;
      else // reduced problem is (n - 1)!
         // general problem is n * (n - 1)!
         return n * factorialRecursive(n - 1);
   }
   
   public static int expRecursive(int x, int y) {
      if(y == 0)
         return 1;
      else if(y == 1)
         return x;
      else
         return x * expRecursive(x, y - 1);
   }
   
   public static void main(String[] args) {
      int n = 4;
      int x = 2;
      int y = 16;
      
      System.out.println(n + "! = " + factorialIterative(n) );
      System.out.println(n + "! = " + factorialRecursive(n) );
      System.out.println(n + "! = " + factorialRecursiveTrace(n));
      System.out.println(x + "^" + y + " = " + expRecursive(x, y));
   }
}