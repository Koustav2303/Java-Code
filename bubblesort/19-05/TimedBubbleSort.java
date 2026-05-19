import java.util.Arrays;

public class TimedBubbleSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {100, 43, 65, 23, 98, 12, 5, 87, 34, 56};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // Start the timer
        long startTime = System.nanoTime();
        
        sort(numbers);
        
        // Stop the timer
        long endTime = System.nanoTime();
        
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
        System.out.println("Sorting took: " + (endTime - startTime) + " nanoseconds.");
    }
}