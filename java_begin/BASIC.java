import java.util.LinkedList;
import java.util.ListIterator;

public class BASIC {
    // initialize the linelist linked list
    LinkedList<Integer> linelist = new LinkedList<>();

    private class Line {
        int lineNumber; // The line number of the code
        String code; // The code

        // Constructor
        public Line(int lineNumber, String code) {
            this.lineNumber = lineNumber;
            this.code = code;
        }

        @Override
        public String toString() {
            return lineNumber + ": " + code;
        }
    }

    // LinkedList to store code
    private LinkedList<Line> storeCode = new LinkedList<>();

    public BASIC() {
        storeCode = new LinkedList<>();
    }

    // Add a line to the program
    public void addLine(int lineNumber, String code) {
        storeCode.add(new Line(lineNumber, code));
    }

    // get a line number to the linked list linelist
    public void addLineNumber(int lineNumber) {
        // add lineNumber to the linelist linkedlist
        linelist.add(lineNumber);
    }

    public void listAll() {
        // this is to check if the linelist is empty
        if (linelist.isEmpty()) {
            System.out.println("No line numbers stored.");
            return;
        }
        System.out.println("All Line Numbers:");
        // list all linenumbers stored in listlist
        for (int lineNumber : linelist) {
            // print the line number
            System.out.println(lineNumber);
        }
    }

    // list a range of line numbers between 'first' and 'last'
    public void listRange(int first, int last) {
        // check if the list range is empty
        if (linelist.isEmpty()) {
            System.out.println("No line numbers stored.");
            return;
        }

        // check if it is within the range, for lineNumber in linelist
        for (int lineNumber : linelist) {
            // if it's in the range first and last
            if (lineNumber >= first && lineNumber <= last) {
                // print the line number
                System.out.println(lineNumber);
            }
        }
    }

    public void insert(int lineNumber) {
        // Create an iterator for the list
        ListIterator<Integer> iter = linelist.listIterator();
        Integer previous = null;
        boolean inserted = false;

        // loop through the iterator
        while (iter.hasNext()) {
            Integer next = iter.next();

            // Check if the lineNumber already exists
            if (next == lineNumber) {
                System.out.println("Error: Line number " + lineNumber + " already exists. Cannot insert.");
                return;
            }

            // Insert before the next line number if lineNumber is smaller than next number
            if (lineNumber < next) {
                iter.previous(); // Move the iterator back
                iter.add(lineNumber); // Insert the new line number
                inserted = true;
                break;
            }

            // Move to the next element
            previous = next;
        }

        // If not inserted add to the end
        if (!inserted) {
            linelist.add(lineNumber);
        }

        // Print the inserted line number
        System.out.println("Inserted line number: " + lineNumber);
    }

    public void renumber() {
        int currentLineNumber = 10; // Start from 10
        for (Line line : storeCode) {
            line.lineNumber = currentLineNumber; // Update the line number
            currentLineNumber += 10; // Increment by 10 next line
        }
        System.out.println("Program renumbered.");
    }

    // use list iterator here so i dont get dead code
    public void modify(int lineNumber, int newLineNumber) {
        ListIterator<Line> iterator = storeCode.listIterator();
        Line lineToModify = null;

        // find the line to modify
        while (iterator.hasNext()) {
            Line currentLine = iterator.next();
            if (currentLine.lineNumber == lineNumber) {
                lineToModify = currentLine;
            } else if (currentLine.lineNumber == newLineNumber) {
                // If newLineNumber is already in use, print error and return
                System.out.println("Error: Line " + newLineNumber + " is already used.");
                return;
            }
        }

        if (lineToModify != null) {
            // If the line to modify is found, update to new line number
            lineToModify.lineNumber = newLineNumber;
            System.out.println("Line " + lineNumber + " moved to Line " + newLineNumber);
        } else {
            // If lineNumber doesn't exist, print an error
            System.out.println("Error: Line " + lineNumber + " does not exist.");
        }
    }

    public static void main(String[] args) {
        BASIC testCases = new BASIC();
    
        // Insert initial lines 10 to 100
        for (int i = 10; i <= 100; i += 10) {
            testCases.addLineNumber(i);
            testCases.addLine(i, "Code for line " + i);
        }
    
        // all lines of the program
        System.out.println("Test Case 1:");
        testCases.listAll();
    
        // lines 40-80
        System.out.println("\nTest Case 2:");
        testCases.listRange(40, 80);
    
        // Insert Line 17
        System.out.println("\nTest Case 3:");
        testCases.insert(17);
    
        // Insert Line 34
        System.out.println("\nTest Case 4:");
        testCases.insert(34);
    
        // Insert Line 88
        System.out.println("\nTest Case 5:");
        testCases.insert(88);
    
        //  lines after insertions
        System.out.println("\nTest Case 6:");
        testCases.listAll();
    
        // Renumber the program
        System.out.println("\nTest Case 7:");
        testCases.renumber();
    
        // lines after renumbering
        System.out.println("\nTest Case 8:");
        testCases.listAll();
    
        // Insert Line 80 
        System.out.println("\nTest Case 9:");
        testCases.insert(80);
    
        // Modify Line 9 
        System.out.println("\nTest Case 10:");
        testCases.modify(9, 50);
    
        // Modify Line 10 to Line 110 
        System.out.println("\nTest Case 11:");
        testCases.modify(10, 110);
    
        // Modify Line 10 to Line 111 
        System.out.println("\nTest Case 12:");
        testCases.modify(10, 111);
    
        // lines after modification
        System.out.println("\nTest Case 13:");
        testCases.listAll();
    
        // Renumber the program 
        System.out.println("\nTest Case 14:");
        testCases.renumber();
    
        // List all lines 
        System.out.println("\nTest Case 15:");
        testCases.listAll();
    }
    
}
