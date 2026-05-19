import java.util.Arrays;

public class OddEvenBubbleSort {
    public static void sort(int[] arr) {
        boolean isSorted = false;
        int n = arr.length;

        while (!isSorted) {
            isSorted = true;

            // Perform Bubble sort on odd indexed elements
            for (int i = 1; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }

            // Perform Bubble sort on even indexed elements
            for (int i = 0; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {34, 2, 10, -9, 3, 5, 12};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}