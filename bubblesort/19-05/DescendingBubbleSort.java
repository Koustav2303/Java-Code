import java.util.Arrays;

public class DescendingBubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) { // Switched to <
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 50, 20, 80, 30};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}