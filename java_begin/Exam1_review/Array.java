package Exam1_review;

import java.util.Arrays; // Import needed for Arrays.toString()

public class Array {
    public static void main(String[] args) {
        // Array operations
        int[] array = { 1, 2, 3, 4, 5 };

        // Fetch
        int element = array[2]; // Accessing the third element
        System.out.println("Fetched element: " + element);

        // Insert (requires shifting)
        int insertIndex = 2;
        int valueToInsert = 99;
        // Create a new array to hold the result
        int[] newArr = new int[array.length + 1];
        for (int i = 0, j = 0; i < newArr.length; i++) {
            if (i == insertIndex) {
                newArr[i] = valueToInsert;
            } else {
                newArr[i] = array[j++];
            }
        }

        // Print the new array after insertion
        System.out.println("Array after insertion: " + Arrays.toString(newArr));

        // Update
        array[1] = 10; // Updating the second element
        System.out.println("Array after update: " + Arrays.toString(array));

        // Delete (requires shifting)
        int deleteIndex = 2;
        // Create a new array with one less element
        int[] newArry = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == deleteIndex) {
                continue; // Skip the element to be deleted
            }
            newArry[j++] = array[i];
        }

        // Print the new array after deletion
        System.out.println("Array after deletion: " + Arrays.toString(newArry));
    }
}
