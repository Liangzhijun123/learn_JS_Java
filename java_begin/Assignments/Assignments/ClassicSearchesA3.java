package Assignments;

import java.util.*;

public class ClassicSearchesA3 {
   static int ops = 0;
   static final int DATA_SIZE = 1000;
   static final int NUM_DATASETS = 100;
   static final int MAX_VALUE = 10000;

   static int linearSearch(int[] arr, int key) {
      ops = 0;
      for (int i = 0; i < arr.length; i++) {
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
         int index = low + (((key - arr[low]) * (high - low)) / (arr[high] - arr[low]));
         if (arr[index] == key)
            return index;
         if (arr[index] < key)
            low = index + 1;
         else
            high = index - 1;
      }
      return -1;
   }

   static void SuccessfulSearches(int[] dataSet) {
      double totalLinearOps = 0;
      double totalBinaryOps = 0;
      double totalInterpolationOps = 0;

      for (int key : dataSet) {
         linearSearch(dataSet, key);
         totalLinearOps += ops;

         binarySearch(dataSet, key);
         totalBinaryOps += ops;

         interpolationSearch(dataSet, key);
         totalInterpolationOps += ops;
      }

      System.out.println("Successful Search Operations:");
      System.out.println("- Linear Search: " + (totalLinearOps / DATA_SIZE));
      System.out.println("- Binary Search: " + (totalBinaryOps / DATA_SIZE));
      System.out.println("- Interpolation Search: " + (totalInterpolationOps / DATA_SIZE));
   }

   static int[] unsuccessSearch(int[] dataSet) {
      HashSet<Integer> dataSetSet = new HashSet<>();
      for (int num : dataSet) {
         dataSetSet.add(num);
      }

      Random rand = new Random();
      int[] unsuccessSearch = new int[DATA_SIZE];
      int count = 0;

      while (count < DATA_SIZE) {
         int randomNum = rand.nextInt(MAX_VALUE) + 1;
         if (!dataSetSet.contains(randomNum)) {
            unsuccessSearch[count++] = randomNum;
         }
      }

      return unsuccessSearch;
   }

   static void runUnsuccessful(int[] dataSet) {
      int[] unsuccessSearch = unsuccessSearch(dataSet);
      double totalLinearOps = 0;
      double totalBinaryOps = 0;
      double totalInterpolationOps = 0;

      for (int key : unsuccessSearch) {
         linearSearch(dataSet, key);
         totalLinearOps += ops;

         binarySearch(dataSet, key);
         totalBinaryOps += ops;

         interpolationSearch(dataSet, key);
         totalInterpolationOps += ops;
      }

      System.out.println("Unsuccessful Search Operations:");
      System.out.println("- Linear Search: " + (totalLinearOps / DATA_SIZE));
      System.out.println("- Binary Search: " + (totalBinaryOps / DATA_SIZE));
      System.out.println("- Interpolation Search: " + (totalInterpolationOps / DATA_SIZE));
   }

   static void generateDataSets() {
      Random rand = new Random();
      HashSet<Integer> uniqueNumbers = new HashSet<>();
   
      for (int i = 0; i < NUM_DATASETS; i++) {
         int[] dataSet = new int[DATA_SIZE];
         int count = 0;
   
         while (count < DATA_SIZE) {
            int randomNum = rand.nextInt(MAX_VALUE) + 1;
           
            if (uniqueNumbers.add(randomNum)) {
               dataSet[count++] = randomNum;
            }
         }
   
         Arrays.sort(dataSet);
         SuccessfulSearches(dataSet);  
         runUnsuccessful(dataSet);     
      }
   }
   
   public static void main(String[] args) {
      int key;
      int[] nums = {15, 98, 7, 22, 9, 61, 57};

      Arrays.sort(nums);
      printArray(nums);
      key = 78;
      searchResult("Linear", key, linearSearch(nums, key));
      searchResult("Binary", key, binarySearch(nums, key));
      searchResult("Interpolation", key, interpolationSearch(nums, key));

      generateDataSets();
   }

   static void printArray(int arr[]) {
      for (int num : arr) {
         System.out.print(num + " ");
      }
      System.out.println();
   }

   static void searchResult(String type, int key, int index) {
      if (index != -1)
         System.out.println(type + ": Found " + key + " at index " + index + " in " + ops + " operations");
      else
         System.out.println(type + ": Did not find " + key + " in " + ops + " operations");
   }
}
