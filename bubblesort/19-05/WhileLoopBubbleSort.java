import java.util.Arrays;

public class WhileLoopBubbleSort {
    public static void sort(int[] arr) {
        boolean swapped = true;
        int n = arr.length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            n--; 
        }
    }

    public static void main(String[] args) {
        int[] numbers = {100, 23, 45, 12, 8, 3};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}