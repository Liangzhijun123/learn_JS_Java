package Exam1_review;

public class FactorialRecursive {
    public static int factorial(int n) { //factorial of a number is 'n'
        // Base case
        if (n == 0) {  //0! = 1 -> n == 0 is 1
            return 1;
        }
        // Recursive case
        return n * factorial(n - 1); // the formula

        /*   
         * Factorial 5 
         * 
         * factorial(0) = 1 base case
         * factorial(1) = 1 * (1-1) -> 1 * factorial(0) -> 1 * 1 = 1
         * 
         */
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println("Factorial of " + number + " is: " + factorial(number));
    }
}
