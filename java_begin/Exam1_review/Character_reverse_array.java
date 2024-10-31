package Exam1_review;



public class Character_reverse_array {
    public String reverseWithArray(String str) {
        // covert input string 'str' into a character array
        char[] charArray = str.toCharArray();

        // start at the beginning of the array (index 0)
        int left = 0;

        // start at the end of the array (index charArray -1)
        int right = charArray.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = charArray[left]; //left index charArray[left]

           // assign right index charArray[right] to charArray [left]
            charArray[left] = charArray[right]; 

            //assign temp (orifional left character) to charArray[right]
            charArray[right] = temp;
            
            // move the pointing to next character
            left++;
            right--;
        }
        
        return new String(charArray);
    }

    public static void main (String[] args){
        Character_reverse_array reverse = new Character_reverse_array();

        // Test cases
        String test1 = "hello"; 
        String result1 = reverse.reverseWithArray(test1);
        System.out.println("Original: " + test1 + " | Reversed: " + result1);  // Expected: olleh

        String test2 = "world";
        String result2 = reverse.reverseWithArray(test2);
        System.out.println("Original: " + test2 + " | Reversed: " + result2);  // Expected: dlrow

        String test3 = "Java";
        String result3 = reverse.reverseWithArray(test3);
        System.out.println("Original: " + test3 + " | Reversed: " + result3);  // Expected: avaJ

        // Edge case: Empty string
        String test4 = "";
        String result4 = reverse.reverseWithArray(test4);
        System.out.println("Original: '" + test4 + "' | Reversed: '" + result4 + "'");  // Expected: ''

        // Edge case: Single character
        String test5 = "A";
        String result5 = reverse.reverseWithArray(test5);
        System.out.println("Original: " + test5 + " | Reversed: " + result5);  // Expected: A
    }
}
