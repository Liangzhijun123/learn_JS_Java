

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringSearch {

   static List<Integer> rabinKarpMultiple(String str, String substr) {
      int n = str.length();
      int m = substr.length();
      int t = 0; //string window
      int p = 0; 
      List<Integer> occurrences = new ArrayList<>();

    
      for (int i = 0; i < m; i++) {
         p += substr.charAt(i);
         t += str.charAt(i);
      }

      for (int i = 0; i <= n - m; i++) {
     
         if (p == t) {
            int j;
            for (j = 0; j < m; j++) {
               if (substr.charAt(j) != str.charAt(i + j)) {
                  break;
               }
            }
            if (j == m) {
               occurrences.add(i);
            }
         }

       
         if (i < n - m) {
            t = t - str.charAt(i) + str.charAt(i + m);
         }
      }
      return occurrences;
   }

   static void countBases(String sequence) {
      HashMap<Character, Integer> baseCount = new HashMap<>();
      baseCount.put('A', 0);
      baseCount.put('C', 0);
      baseCount.put('T', 0);
      baseCount.put('G', 0);

      for (char base : baseCount.keySet()) {
         List<Integer> occurrences = rabinKarpMultiple(sequence, String.valueOf(base));
         baseCount.put(base, occurrences.size());
      }

      System.out.println("Base counts:");
      for (char base : baseCount.keySet()) {
         System.out.println(base + ": " + baseCount.get(base));
      }
   }

   public static void main(String[] args) {
      String dnaSequence = "GTTGCAGTTACTTATTATCTGAAAACCAGTTGATGTTAAGGAATACTCTGTCTAAGACAACATATGTAATAAAAATTATATATTCGTTGGGTTCTCTCGA";
      String target = "GTT";

   
      List<Integer> positions = rabinKarpMultiple(dnaSequence, target);
      System.out.println("Positions of 'GTT': " + positions);
      countBases(dnaSequence);
   }
}
