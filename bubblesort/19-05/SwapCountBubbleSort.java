import java.util.Arrays;

public class SwapCountBubbleSort {
    public static void sortAndCount(int[] arr) {
        int n = arr.length;
        int swapCount = 0;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
        }
        System.out.println("Total swaps made: " + swapCount);
    }

    public static void main(String[] args) {
        int[] numbers = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; // Worst case scenario
        System.out.println("Original array: " + Arrays.toString(numbers));
        sortAndCount(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}