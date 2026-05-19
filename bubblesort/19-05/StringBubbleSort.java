import java.util.Arrays;

public class StringBubbleSort {
    public static void sort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"Zebra", "Apple", "Mango", "Banana"};
        System.out.println("Original array: " + Arrays.toString(words));
        sort(words);
        System.out.println("Sorted array:   " + Arrays.toString(words));
    }
}