package java_begin.week_1;

public class reverse {
    public static String stringReverse(String in) {
        int len = in.length();
        char[] inCharArray = in.toCharArray();

        // new character of the array
        char[] reverse = new char[len];

        // loop start from 0 to the length of the string
        for (int i = 0; i < len; i++)

        // going from begining from index, accessing the last character of array
        // keep decreaseing, length - index - 1. 
            reverse[i] = inCharArray[len - i - 1];

        // valueOf is a stastic call, dont need to declare the object to call the method
        return String.valueOf(reverse);

    }

    public static void main(String[] args) {
        String str = new String("This is a string");
        System.out.println("Before method: " + str);
        str = stringReverse(str);
        System.out.println("After method = " + str);
    }
}
