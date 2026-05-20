import java.util.Arrays;

public class RecursiveBinarySearch {
    public static int search(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Target not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return search(arr, target, mid + 1, right);
        }
        return search(arr, target, left, mid - 1);
    }

    public static void main(String[] args) {
        int[] numbers = {3, 11, 24, 35, 49, 58, 67, 82, 99};
        int target = 67;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int result = search(numbers, target, 0, numbers.length - 1);
        
        System.out.println("Target " + target + " found at index: " + result);
    }
}