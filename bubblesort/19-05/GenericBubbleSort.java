import java.util.Arrays;

public class GenericBubbleSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Double[] decimals = {4.5, 2.1, 8.9, 1.2, 5.5};
        System.out.println("Original array: " + Arrays.toString(decimals));
        sort(decimals);
        System.out.println("Sorted array:   " + Arrays.toString(decimals));
    }
}