import java.util.Arrays;
import java.util.Stack;

public class IterativeQuickSort {
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void sort(int[] arr, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            int pi = partition(arr, low, high);

            if (pi - 1 > low) {
                stack.push(low);
                stack.push(pi - 1);
            }
            if (pi + 1 < high) {
                stack.push(pi + 1);
                stack.push(high);
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {43, 3, 20, 89, 4, 77, 12};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Iteratively Sorted: " + Arrays.toString(numbers));
    }
}