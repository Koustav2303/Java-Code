import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListBubbleSort {
    public static void sort(ArrayList<Integer> list) {
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
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(99, 44, 22, 11, 66));
        System.out.println("Original list: " + list);
        sort(list);
        System.out.println("Sorted list:   " + list);
    }
}