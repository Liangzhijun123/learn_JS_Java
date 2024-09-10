import java.util.Stack;

public class BalancerStarter {
   static String expression = "-{ [ b * b – (4 * a * c) ] / (2 * a) }";

   // if char are patching pairs
   static boolean MatchPair(char a, char b) {
      if (a == '(' && b == ')')
         return true;
      else if (a == '[' && b == ']')
         return true;
      else if (a == '{' && b == '}')
         return true;
      return false;
   }

   static boolean isBalanced(String expression) {
      Stack<Character> stack = new Stack<Character>();

      for (int i = 0; i < expression.length(); i++) {
         char current = expression.charAt(i);

         // when you see opening character, add to stack.push(current)
         if (current == '(' || current == '{' || current == '[') {
            stack.push(current);
         }

         // when u see a closing character, pop from the stack
         if (current == ')' || current == '}' || current == ']') {

            // if the stack is empty, return true, otherwise is unbalance return false
            if (stack.isEmpty() || !MatchPair(stack.pop(), current)) {
               return false;
            }
         }
      }

      return stack.isEmpty();
   }

   public static void main(String[] args) {

      String test1 = "-{ [ b * b – (4 * a * c) ] / (2 * a) }";
      String test2 = "–[ { [ b * b – (4 * a * c) ] / (2 * a) }";
      String test3 = "–{ [ b * b – (4 * a * c) ] / (2 * a) } ]";
      String test4 = "–{ [ b * b – { (4 * a * c) ] / (2 * a) }";

      if (isBalanced(test1)) {
         System.out.println(test1 + " is balanced");
      } else {
         System.out.println(test1 + " is not balanced");
      }

      if (isBalanced(test2)) {
         System.out.println(test2 + " is balanced");
      } else {
         System.out.println(test2 + " is not balanced");
      }

      if (isBalanced(test3)) {
         System.out.println(test3 + " is balanced");
      } else {
         System.out.println(test3 + " is not balanced");
      }

      if (isBalanced(test4)) {
         System.out.println(test4 + " is balanced");
      } else {
         System.out.println(test4 + " is not balanced");
      }

   }
}