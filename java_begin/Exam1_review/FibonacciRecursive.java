package Exam1_review;

public class FibonacciRecursive {
    public static int fibonacci(int n) {
        // Base cases
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
        /*
         * Fibonacci(5)
         * fibonacci(0) = 0 (base case)
         * fibonacci(1) = 1 (base case)
         * fibonacci(2) = fibonacci(2-1) + fibonacci(2-2) -> fibonacci(1) + fibonacci(0) 
         */
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println("Fibonacci of " + number + " is: " + fibonacci(number));
    }
}
