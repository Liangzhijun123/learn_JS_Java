package java_begin.week_1;
// I FORGOT EVERYTHING 

public class starter {

   public static String stringReverse(String in) {
      //inital empty string
      String arr = "";
      // the integer number equal the same as the string of length to convert integer to length of the string.
      int Number = in.length();

      // going to loop here
      for (int i = Number - 1; i >=0; i--) {
         arr += in.charAt(i);
      }
      return arr;
   }

   

   public static int patternFinder(String sequence, String pattern) {
      int seqLength = sequence.length();
      int patLength = pattern.length();

       // Check for invalid inputs -- If the method does not find the pattern in the sequence, return a -1. 
       if (patLength == 0 || seqLength == 0 || patLength > seqLength) {
         return -1;
     }
      return -1;
   }

   public static void main(String[] args) {
      String input = new String("This is a string");

      // original string
      System.out.println("Before method: " + input);

      // calling from the stringRever method
      String output = stringReverse(input);
      System.out.println("After method: " + output);
      
      // hard coded stuff
      String sequence = new String("GTGGCAGTTACTTA"); // GTT starts at index 6
      String pattern = new String("GTT");
      System.out.println("Index = " + patternFinder(sequence, pattern));
   }
}
