import java.util.Arrays;

public class AbsoluteValueBubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare the absolute values using Math.abs
                if (Math.abs(arr[j]) > Math.abs(arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {-10, 2, 5, -3, 8, -1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted by absolute value: " + Arrays.toString(numbers));
    }
}