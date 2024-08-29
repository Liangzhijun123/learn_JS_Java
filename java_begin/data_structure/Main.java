package java_begin.data_structure;

public class Main {
    public static void main (String[] args){
        DynamicArray dynamic = new DynamicArray();

        dynamic.add("A");
        dynamic.add("B");
        dynamic.add("C");
        System.out.println(dynamic);
        System.out.println("size: " + dynamic.size);
        System.out.println("capacity: " + dynamic.capacity);
        System.out.println("empty: " + dynamic.isEmpty());

    }
}
