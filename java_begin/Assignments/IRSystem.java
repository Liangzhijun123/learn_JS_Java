import java.util.*;
import java.io.*;

public class IRSystem {
    class Node {
        TermEntry entry;
        Node left, right;

        public Node(TermEntry entry) {
            this.entry = entry;
            left = right = null;
        }
    }

    class TermEntry {
        private String term;
        private int count;

        public TermEntry(String term, int count) {
            this.term = term;
            this.count = count;
        }

        public String getTerm() {
            return term;
        }

        public int getCount() {
            return count;
        }
    }

    private Node root;
    private HashMap<String, Integer> termMap;

    public IRSystem() {
        root = null;
        termMap = new HashMap<>();
    }

    public String[] parse(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line.toLowerCase()).append(" ");
        }
        reader.close();
        return sb.toString().split("\\W+");
    }

    public void countTerms(String[] terms) {
        for (String term : terms) {
            termMap.put(term, termMap.getOrDefault(term, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : termMap.entrySet()) {
            insert(new TermEntry(entry.getKey(), entry.getValue()));
        }
    }

    public void insert(TermEntry entry) {
        root = insertMethod(root, entry);
    }

    private Node insertMethod(Node root, TermEntry entry) {
        if (root == null) {
            root = new Node(entry);
            return root;
        }
        if (entry.getTerm().compareTo(root.entry.getTerm()) < 0) {
            root.left = insertMethod(root.left, entry);
        } else if (entry.getTerm().compareTo(root.entry.getTerm()) > 0) {
            root.right = insertMethod(root.right, entry);
        }
        return root;
    }

    public TermEntry singleTermQuery(Node node, String term) {
        if (node == null) {
            return null;
        }

        if (node.entry.getTerm().equals(term)) {
            return node.entry;
        }

        if (term.compareTo(node.entry.getTerm()) < 0) {
            return singleTermQuery(node.left, term);
        } else {
            return singleTermQuery(node.right, term);
        }
    }

    // AND query
    public boolean andQuery(String term1, String term2) {
        return (singleTermQuery(root, term1) != null && singleTermQuery(root, term2) != null);
    }

    // OR query
    public boolean orQuery(String term1, String term2) {
        return (singleTermQuery(root, term1) != null || singleTermQuery(root, term2) != null);
    }

    // Inorder traversal
    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.entry.getTerm() + ": " + node.entry.getCount());
            inorder(node.right);
        }
    }

    public static void main(String[] args) throws IOException {
        IRSystem irsystem = new IRSystem();

        String filename = "C:\\Users\\liang\\Desktop\\projects\\Datastructure_class\\java_begin\\Assignments\\quotes.txt";

        String[] terms = irsystem.parse(filename);

        irsystem.countTerms(terms);

        System.out.println("Inorder traversal of terms:");
        irsystem.inorder(irsystem.root);

        System.out.println("Single term query 'all': " + irsystem.singleTermQuery(irsystem.root, "all"));
        System.out.println("Single term query 'carrying': " + irsystem.singleTermQuery(irsystem.root, "carrying"));
        System.out.println("Single term query 'robot': " + irsystem.singleTermQuery(irsystem.root, "robot"));
        System.out.println("AND query 'seattle' and 'mariners': " + irsystem.andQuery("seattle", "mariners"));
        System.out.println("AND query 'seattle' and 'pilots': " + irsystem.andQuery("seattle", "pilots"));
        System.out.println("OR query 'four' and 'score': " + irsystem.orQuery("four", "score"));
        System.out.println("OR query 'five' and 'score': " + irsystem.orQuery("five", "score"));
        System.out.println("OR query 'five' and 'robots': " + irsystem.orQuery("five", "robots"));

        irsystem.countTerms(terms);

        System.out.println("Inorder traversal of terms:");
        irsystem.inorder(irsystem.root);

        // Test Case 1
        TermEntry allEntry = irsystem.singleTermQuery(irsystem.root, "all");
        if (allEntry != null) {
            System.out.println("Single term query 'all': Found, Count = " + allEntry.getCount());
        } else {
            System.out.println("Single term query 'all': Not Found");
        }

        // Test Case 2
        TermEntry carryingEntry = irsystem.singleTermQuery(irsystem.root, "carrying");
        if (carryingEntry != null) {
            System.out.println("Single term query 'carrying': Found, Count = " + carryingEntry.getCount());
        } else {
            System.out.println("Single term query 'carrying': Not Found");
        }

        // Test Case 3
        TermEntry robotEntry = irsystem.singleTermQuery(irsystem.root, "robot");
        if (robotEntry != null) {
            System.out.println("Single term query 'robot': Found, Count = " + robotEntry.getCount());
        } else {
            System.out.println("Single term query 'robot': Not Found");
        }

        // Test Case 4
        if (irsystem.andQuery("seattle", "mariners")) {
            System.out.println("AND query 'seattle' and 'mariners': Successful");
        } else {
            System.out.println("AND query 'seattle' and 'mariners': Unsuccessful");
        }

        // Test Case 5
        if (irsystem.andQuery("seattle", "pilots")) {
            System.out.println("AND query 'seattle' and 'pilots': Successful");
        } else {
            System.out.println("AND query 'seattle' and 'pilots': Unsuccessful");
        }

        // Test Case 6
        if (irsystem.orQuery("four", "score")) {
            System.out.println("OR query 'four' and 'score': Successful");
        } else {
            System.out.println("OR query 'four' and 'score': Unsuccessful");
        }

        // Test Case 7
        if (irsystem.orQuery("five", "score")) {
            System.out.println("OR query 'five' and 'score': Successful");
        } else {
            System.out.println("OR query 'five' and 'score': Unsuccessful");
        }

        // Test Case 8
        if (irsystem.orQuery("five", "robots")) {
            System.out.println("OR query 'five' and 'robots': Successful");
        } else {
            System.out.println("OR query 'five' and 'robots': Unsuccessful");
        }

    }

}
