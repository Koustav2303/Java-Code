import java.util.LinkedList;
import java.util.Arrays;

public class LinkedListBubbleSort {
    public static void sort(LinkedList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(77, 33, 99, 11, 44));
        System.out.println("Original LinkedList: " + list);
        sort(list);
        System.out.println("Sorted LinkedList:   " + list);
    }
}