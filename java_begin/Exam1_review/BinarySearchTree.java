package Exam1_review;

public class BinarySearchTree {
    class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root;

    // Insert method
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data); //if root is empty, create new Node, the first node 'data' is the Head
            return root;
        }
        // 50,30,70,20,40,60,80
        // data = 30. root.data (50)
        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);
        return root;
    }

    // Search method
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
    }

     // New delete method that returns the deleted value
     Integer delete(int value) {
        if (search(value)) {
            root = deleteRec(root, value);
            return value;
        } else {
            return null; // Value not found
        }
    }

    private Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }

        // Traverse the tree to find the node to delete
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Find the smallest node in the right subtree (in-order successor)
            root.data = findMinValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    

    private int findMinValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

   

     // In-order traversal (ascending order for BST)
     public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    // Pre-order traversal
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Post-order traversal
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert values into the tree
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Search for a value
        System.out.println("Search 40: " + bst.search(40)); // true
        System.out.println("Search 25: " + bst.search(25)); // false

        
        
        // Print values using in-order traversal (ascending order)
        System.out.print("In-order traversal: ");
        bst.inOrder();

        // Print values using pre-order traversal
        System.out.print("Pre-order traversal: ");
        bst.preOrder();

        // Print values using post-order traversal
        System.out.print("Post-order traversal: ");
        bst.postOrder();

        // Delete a node and check if it still exists
        Integer deletedValue = bst.delete(70);
        if (deletedValue != null) {
            System.out.println("Deleted value: " + deletedValue);
        } else {
            System.out.println("Value not found.");
        }

        System.out.println("Tree after deletion (in-order): ");
        bst.inOrder();  // Print the tree after the deletion

        Integer deletedValue1 = bst.delete(80);
        if (deletedValue1 != null) {
            System.out.println("Deleted value: " + deletedValue1);
        } else {
            System.out.println("Value not found.");
        }

        System.out.println("Tree after deletion (in-order): ");
        bst.inOrder();  // Print the tree after the deletion
    }
}
