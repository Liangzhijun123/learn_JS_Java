package Assignments;

public class Dijkstra { 
   
   static int[] dijkstra(int graph[][], int source) { 
      int N = graph.length;
      // denotes shortest distance from source node to all other nodes
      int distances[] = new int[N]; 
      // indicates if the node has been visited or not (defaults to false)
      boolean visited[] = new boolean[N]; 
   
      // initialize shortest distance to all nodes as "infinity" 
      for (int i = 0; i < N; i++)
         distances[i] = Integer.MAX_VALUE; 
    
      distances[source] = 0; // distance from source node to itself is 0
   
      // find shortest path to all nodes 
      for(int count = 0; count < N - 1; count++) { 
         // choose the minimum distance node from the set of nodes 
         // not yet visited 
         int min = Integer.MAX_VALUE;
         int minIndex = -1; 
      
         for(int i = 0; i < N; i++) {
            if(!visited[i] && distances[i] <= min) { 
               min = distances[i]; 
               minIndex = i; 
            } 
         }
      
         // mark the minimum distance node as visited
         visited[minIndex] = true; 
         System.out.println("Visiting node " + minIndex);
      
         for(int i = 0; i < N; i++) {
            // Update distances only if node has not been visited, 
            // there is an edge from minimum distance node to node i,
            // and the total length of path from source to node i
            // via minimum distance node is smaller than current value
            // stored in distances 
            if(!visited[i] && graph[minIndex][i] != 0 &&   //this check if there is an edge
               distances[minIndex] + graph[minIndex][i] < distances[i]) //the distance of array is 2d
               
               distances[i] = distances[minIndex] + graph[minIndex][i]; //how i get element of the graph
         } 
      }  
      
      return distances; //return 2d instead of 1d
   } 
   
   static void printDistances(int distances[], char[] nodes) { 
      int N = distances.length;
      System.out.println("Node  Distance"); 
      
      for(int i = 0; i < N; i++) 
         System.out.println(nodes[i] + "\t\t" + distances[i]); 
   }
  
   // make a printPath function
   /*
    * take the distance and node names, and what is the source. it will output the path. path A to B is this
     - start at the end of the graph and look at dikstra table what is the prev node to the node that i find the path with
     - build the path backward, build the string whatever the node is
     - did i get to the source node yet?
    */

    
   public static void main(String[] args) //each edge represent twice please. like from G to C, C to G
   { 
      char[] nodeNames = {'A', 'B', 'C', 'D', 'E'}; // Nodes 0, 1, 2, 3, 4
      int graph[][] = new int[][] { { 0, 6, 0, 1, 0 }, 
                                    { 6, 0, 5, 2, 2 }, 
                                    { 0, 5, 0, 0, 5 }, 
                                    { 1, 2, 0, 0, 1 }, 
                                    { 0, 2, 5, 1, 0 } }; 
   
      int distances[] = dijkstra(graph, 0);
      
      printDistances(distances, nodeNames);
   } 
}