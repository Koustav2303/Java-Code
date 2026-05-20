import java.util.Arrays;

public class LastOccurrenceBinarySearch {
    public static int searchLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid; // Record the index
                left = mid + 1; // Keep searching to the right
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
        int index = searchLast(numbers, target);
        
        System.out.println("Last occurrence of " + target + " is at index: " + index);
    }
}