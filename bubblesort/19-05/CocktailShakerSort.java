import java.util.Arrays;

public class CocktailShakerSort {
    public static void sort(int[] arr) {
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;
        
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; ++i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
            swapped = false;
            end = end - 1;
            
            for (int i = end - 1; i >= start; --i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            start = start + 1;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {5, 1, 4, 2, 8, 0, 2};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}