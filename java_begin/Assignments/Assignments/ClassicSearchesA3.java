package Assignments;

import java.util.*;

public class ClassicSearchesA3 {
   static int ops = 0;
   static final int DATA_SIZE = 1000;
   static final int NUM_DATASETS = 100;
   static final int MAX_VALUE = 10000;

   static int linearSearchOrdered(int[] arr, int key) {
      int n = arr.length;
      ops = 0;

      for (int i = 0; i < n; i++) {
         ops++;
         if (arr[i] == key)
            return i;
         else if (arr[i] > key) {
            return -1;
         }
      }

      return -1;
   }

   static int binarySearch(int arr[], int key) {
      int start = 0;
      int end = arr.length - 1;
      ops = 0;

      while (start <= end) {
         // increases by 1
         ops++;
         int mid = (start + end) / 2;
         if (arr[mid] == key)
            return mid;
         else if (arr[mid] < key)
            start = mid + 1;
         else
            end = mid - 1;
      }

      return -1;
   }

   static int interpolationSearch(int[] arr, int key) {
      int low = 0;
      int high = arr.length - 1;
      ops = 0;

      while (low <= high && key >= arr[low] && key <= arr[high]) {
         ops++;
         int index = low + (((key - arr[low]) * (high - low)) /
               (arr[high] - arr[low]));

         if (key == arr[index])
            return index;

         if (key < arr[index])
            high = index - 1;
         else
            low = index + 1;
      }

      return -1;
   }

   static void searchResult(String type, int key, int index) {
      if (index != -1)
         System.out.println(type + ": Found " + key + " at index " + index +
               " in " + ops + " operations");
      else
         System.out.println(type + ": Did not find " + key + " in " + ops +
               " operations");
   }

   static void printArray(int arr[]) {
      int n = arr.length;

      for (int i = 0; i < n; i++)
         System.out.print(arr[i] + " ");

      System.out.println();
   }

   // successful search
   /*
    * 1. Generate a data set with 1000 integers between 1 and 10,000. dont make
    * array 100 by 100. just one array and repopulate everytime with new value.
    * 2. For each element in the data set, find that element using linear, binary,
    * and interpolation search and record how many operations this took to find
    * each element
    * 3. Compute the average number of operations it took to find each element for
    * each search algorithm
    * 4. Repeat Steps 1-3 for a total of 100 times. making 100 sets of random data,
    * sort, and find it.
    * 5. Compute the average number of operations for each search algorithm over
    * the 100 data sets. Wants to do it lots of times to see the avg.
    */

   // unsuccessful search
   /*
    * 1. Generate a data set with 1000 integers between 1 and 10,000. sort it.
    * 2. Generate 1000 random numbers between 1 and 10,000 that are NOT in the data
    * set (use ANY search algorithm to determine whether this number is in the data
    * set or not)
    * a: create a second array with Array that does not have any element in the old
    * array.
    * - to do this: you can use one of the binary search method to saerch for the
    * old array, if is not i can put in the second array.
    * if it is in the old array, then i have to regenerate. the second array can
    * not have the same array as old array
    * 3. Search for the random numbers that were not in the data set using linear,
    * binary, and interpolation search and record how many operations this took for
    * each number.
    * a: search for second array in the element
    * 4. Compute the average number of operations it took to NOT find each number
    * for each search algorithm
    * 5. Repeat Steps 1-4 for a total of 100 times
    * 6. Compute the average number of operations for each search algorithm over
    * the 100 data sets
    */

   static void SuccessfulSearches(int[] dataSet) {
      double[] linearOps = new double[DATA_SIZE];
      double[] binaryOps = new double[DATA_SIZE];
      double[] interpolationOps = new double[DATA_SIZE];

      for (int i = 0; i < DATA_SIZE; i++) {
         int key = dataSet[i];
         linearOps[i] = linearSearchOrdered(dataSet, key);
         binaryOps[i] = binarySearch(dataSet, key);
         interpolationOps[i] = interpolationSearch(dataSet, key);
      }

      double avgLinear = Arrays.stream(linearOps).average().orElse(0);
      double avgBinary = Arrays.stream(binaryOps).average().orElse(0);
      double avgInterpolation = Arrays.stream(interpolationOps).average().orElse(0);

      System.out.printf("Successful Search - Linear: %.2f, Binary: %.2f, Interpolation: %.2f%n", avgLinear, avgBinary,
            avgInterpolation);
   }

   static int[] UnsuccessfulSearch(int[] dataSet) {
      HashSet<Integer> dataSetSet = new HashSet<>();
      for (int num : dataSet) {
         dataSetSet.add(num);
      }

      Random rand = new Random();
      int[] unsuccessfulSearchNumbers = new int[DATA_SIZE];
      int count = 0;

      while (count < DATA_SIZE) {
         int randomNum = rand.nextInt(MAX_VALUE) + 1;
         if (!dataSetSet.contains(randomNum)) {
            unsuccessfulSearchNumbers[count++] = randomNum;
         }
      }

      return unsuccessfulSearchNumbers;
   }

   // Method to run unsuccessful searches
   static void runUnsucessful(int[] dataSet) {
      int[] unsuccessfulSearchNumbers = UnsuccessfulSearch(dataSet);
      double[] linearOps = new double[DATA_SIZE];
      double[] binaryOps = new double[DATA_SIZE];
      double[] interpolationOps = new double[DATA_SIZE];

      for (int i = 0; i < DATA_SIZE; i++) {
         int key = unsuccessfulSearchNumbers[i];
         linearOps[i] = linearSearchOrdered(dataSet, key);
         binaryOps[i] = binarySearch(dataSet, key);
         interpolationOps[i] = interpolationSearch(dataSet, key);
      }

      double avgLinear = Arrays.stream(linearOps).average().orElse(0);
      double avgBinary = Arrays.stream(binaryOps).average().orElse(0);
      double avgInterpolation = Arrays.stream(interpolationOps).average().orElse(0);

      System.out.printf("Unsuccessful Search - Linear: %.2f, Binary: %.2f, Interpolation: %.2f%n", avgLinear, avgBinary,
            avgInterpolation);
   }

   // Method to generate datasets and perform searches
   static void generateDataSets() {
      Random rand = new Random();
      for (int i = 0; i < NUM_DATASETS; i++) {
         int[] dataSet = rand.ints(DATA_SIZE, 1, MAX_VALUE + 1).distinct().limit(DATA_SIZE).toArray();
         Arrays.sort(dataSet);
         runUnsucessful(dataSet);
         runUnsucessful(dataSet);
      }
   }

   public static void main(String[] args) {
      int index;
      int key;
      int[] nums = { 15, 98, 7, 22, 9, 61, 57 };

      Arrays.sort(nums);
      printArray(nums);
      key = 78;
      searchResult("Linear", key, linearSearchOrdered(nums, key));
      searchResult("Binary", key, binarySearch(nums, key));
      searchResult("Interpolation", key, interpolationSearch(nums, key));

      generateDataSets();
   }
}