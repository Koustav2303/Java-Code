import java.util.Arrays;

public class GenericQuickSort {
    public static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Double[] decimals = {9.9, 1.2, 5.5, 3.4, 7.8};
        System.out.println("Original array: " + Arrays.toString(decimals));
        sort(decimals, 0, decimals.length - 1);
        System.out.println("Generic Sorted: " + Arrays.toString(decimals));
    }
}