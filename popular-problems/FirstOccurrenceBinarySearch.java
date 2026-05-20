import java.util.Arrays;

public class FirstOccurrenceBinarySearch {
    public static int searchFirst(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid; // Record the index
                right = mid - 1; // Keep searching to the left
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 4, 4, 4, 6, 8, 9};
        int target = 4;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int index = searchFirst(numbers, target);
        
        System.out.println("First occurrence of " + target + " is at index: " + index);
    }
}