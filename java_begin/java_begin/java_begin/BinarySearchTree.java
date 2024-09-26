package java_begin;

import java.util.Arrays;

class Node {
   Node left;
   Node right;
   int data;

   public Node(int value) {
      data = value;
   }
}

public class BinarySearchTree {
   Node root; // root node of the entire tree

   public BinarySearchTree(int[] keys) {
      // sort keys in ascending order
      Arrays.sort(keys);
      int start = 0;
      int end = keys.length - 1;
      int mid = (start + end) / 2;
      root = new Node(keys[mid]);

      // left side of array passed to left subtree
      insert(root, keys, start, mid - 1);
      // right side of array passed to right subtree
      insert(root, keys, mid + 1, end);
   }

   public void insert(Node node, int[] keys, int start, int end) {
      if (start <= end) {
         int mid = (start + end) / 2;
         if (keys[mid] < node.data) { // left subtree
            node.left = new Node(keys[mid]);
            insert(node.left, keys, start, mid - 1);
            insert(node.left, keys, mid + 1, end);
         } else { // right subtree
            node.right = new Node(keys[mid]);
            insert(node.right, keys, start, mid - 1);
            insert(node.right, keys, mid + 1, end);
         }
      }
   }

   public void preorderTraversal(Node node) {
      if (node != null) {
         System.out.println("Visited " + node.data); //  root node first
         preorderTraversal(node.left); // Visit left subtree
         preorderTraversal(node.right); // Visit right subtree
      }
   }

   public void inorderTraversal(Node node, boolean reverse) {
      if (node != null) {
         if (reverse) {
            inorderTraversal(node.right, true); // Traverse right subtree first 
            System.out.println("Visited " + node.data);
            inorderTraversal(node.left, true); // traverse left subtree
         } else {
            inorderTraversal(node.left, false); // Traverse left subtree first 
            System.out.println("Visited " + node.data);
            inorderTraversal(node.right, false); // traverse right subtree
         }
      }
   }

   int searchDepth;

   public void postorderTraversal(Node node) {
      if (node != null) {
         postorderTraversal(node.left); // Visit left subtree
         postorderTraversal(node.right); // Visit right subtree
         System.out.println("Visited " + node.data); // root node last
      }
   }

   public Node search(Node node, int key, int depth) {
      searchDepth = depth;

      if (node == null) {
         return null; // Unsuccessful search
      }
      if (node.data == key) {
         return node; // return the node
      } else if (key < node.data) {
         return search(node.left, key, depth + 1); // Search  left subtree
      } else {
         return search(node.right, key, depth + 1); // Search  right subtree
      }
   }

   public static void main(String args[]) {
      int[] key_values = { 16, 70, 11, 23, 15, 25, 106 };
      BinarySearchTree bst = new BinarySearchTree(key_values);

  
      System.out.println("Inorder tree traversal (reverse = true)");
      bst.inorderTraversal(bst.root, true);

      //Show the output from the inorder traversal method when reverse is true.
      System.out.println("Preorder tree traversal");
      bst.preorderTraversal(bst.root);

      //Show the output from the postorder traversal method. 
      System.out.println("Postorder tree traversal");
      bst.postorderTraversal(bst.root);

      
      int search_key = 70;
      Node node = bst.search(bst.root, search_key, 0);
      System.out.println("Search for " + search_key + ": Levels deep = " + bst.searchDepth);

      search_key = 16;
      node = bst.search(bst.root, search_key, 0);
      System.out.println("Search for " + search_key + ": Levels deep = " + bst.searchDepth);

      search_key = 110; 
      node = bst.search(bst.root, search_key, 0);
      System.out.println("Search for " + search_key + ": Levels deep = " + bst.searchDepth);
   }

}
