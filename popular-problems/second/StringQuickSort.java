import java.util.Arrays;

public class StringQuickSort {
    public static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void sort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        String[] words = {"Zebra", "Lion", "Tiger", "Ape", "Bear"};
        System.out.println("Original array: " + Arrays.toString(words));
        sort(words, 0, words.length - 1);
        System.out.println("Alphabetical Sorted: " + Arrays.toString(words));
    }
}