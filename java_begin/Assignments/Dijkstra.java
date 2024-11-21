
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

   static class Edge {
      int endNode;
      int weight;

      Edge(int endNode, int weight) {
         this.endNode = endNode;
         this.weight = weight;
      }
   }

   // WeightedGraph adjacency lists
   static class WeightedGraph {
      private final List<List<Edge>> adjacencyList;

      public WeightedGraph(int numNodes) {
         adjacencyList = new ArrayList<>();
         for (int i = 0; i < numNodes; i++) {
            adjacencyList.add(new ArrayList<>());
         }
      }

      public void addEdge(int startNode, int endNode, int weight) {
         adjacencyList.get(startNode).add(new Edge(endNode, weight));
         adjacencyList.get(endNode).add(new Edge(startNode, weight)); 
      }

      public List<Edge> getEdges(int node) {
         return adjacencyList.get(node);
      }

      @Override
      public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < adjacencyList.size(); i++) {
            sb.append("Node ").append(i).append(": ");
            for (Edge edge : adjacencyList.get(i)) {
               sb.append("-> (").append(edge.endNode).append(", ").append(edge.weight).append(") ");
            }
            sb.append("\n");
         }
         return sb.toString();
      }
   }

   // adjacency list
   static int[][] dijkstra(WeightedGraph graph, int source, int numNodes) {
      int[] distances = new int[numNodes];
      int[] previous = new int[numNodes];
      boolean[] visited = new boolean[numNodes];

      for (int i = 0; i < numNodes; i++) {
         distances[i] = Integer.MAX_VALUE;
         previous[i] = -1;
      }
      distances[source] = 0;

      for (int i = 0; i < numNodes; i++) {
         int currentNode = -1;

         // unvisited node with the smallest distance
         for (int j = 0; j < numNodes; j++) {
            if (!visited[j] && (currentNode == -1 || distances[j] < distances[currentNode])) {
               currentNode = j;
            }
         }

         if (distances[currentNode] == Integer.MAX_VALUE)
            break;

         visited[currentNode] = true;

         // Update distances and previous nodes
         for (Edge edge : graph.getEdges(currentNode)) {
            int newDist = distances[currentNode] + edge.weight;
            if (newDist < distances[edge.endNode]) {
               distances[edge.endNode] = newDist;
               previous[edge.endNode] = currentNode;
            }
         }
      }

      return new int[][] { distances, previous };
   }

   // Print distances with previous nodes
   static void printDistances(int[][] results, char[] nodes) {
      System.out.println("Node\tDistance\tPrevious Node");
      for (int i = 0; i < nodes.length; i++) {
         String prevNode = results[1][i] == -1 ? "None" : String.valueOf(nodes[results[1][i]]);
         System.out.println(nodes[i] + "\t" + results[0][i] + "\t\t" + prevNode);
      }
   }

   // Print paths from source to nodes
   static void printPaths(int[][] results, char[] nodes, int source) {
      for (int i = 0; i < nodes.length; i++) {
         if (i == source)
            continue;
         List<Character> path = new ArrayList<>();
         for (int at = i; at != -1; at = results[1][at]) {
            path.add(0, nodes[at]);
         }
         System.out.println("Shortest path from " + nodes[source] + " to " + nodes[i] + " is: "
               + String.join(", ", path.toString()));
      }
   }

   public static void main(String[] args) {
      char[] nodeNames = { 'A', 'B', 'C', 'D', 'E', 'G' };
      WeightedGraph graph = new WeightedGraph(6);

      graph.addEdge(5, 2, 5);
      graph.addEdge(2, 3, 4);
      graph.addEdge(2, 1, 1);
      graph.addEdge(1, 0, 1);
      graph.addEdge(0, 3, 1);
      graph.addEdge(3, 5, 6);
      graph.addEdge(3, 4, 4); 
      graph.addEdge(5, 4, 3); 
  

      System.out.println("Graph adjacency list:");
      System.out.println(graph);

      int[][] results = dijkstra(graph, 5, 6);

      printDistances(results, nodeNames);
      printPaths(results, nodeNames, 5);
   }

}
