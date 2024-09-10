package java_begin;

public class swap_variable {
    public static void main(String[] args) {
        String x = "water";
        String y = "kool aid";
        String temp;

        temp = x;
        x = y;
        y = temp;

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("temp: " + temp);
    }
}
