import java.util.Arrays;

public class StandardBubbleSort {
    
    // The sorting logic
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

    // The entry point for the program
    public static void main(String[] args) {
        // 1. Create an unsorted array
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // 2. Call the sort method
        sort(numbers);
        
        // 3. Print the sorted array
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}