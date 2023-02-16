package hw2;

public class Main {
    public static void main(String[] args) {
        IndexedList<Integer> list = new SparseIndexedList<>(10, 0);

        list.put(5, 3);
        list.put(5,4);
        list.put(6,4);
        list.put(7, 2);
        list.put(6,null);
        list.put(6,0);

        for (int element : list) {
            System.out.println(element);
        }

    }
}
