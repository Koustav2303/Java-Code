import java.util.Arrays;

public class CeilingBinarySearch {
    public static int searchCeiling(int[] arr, int target) {
        if (target > arr[arr.length - 1]) return -1; // No ceiling exists

        int left = 0, right = arr.length - 1;
        int ceilingIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Exact match is its own ceiling
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                ceilingIndex = mid; // Potential ceiling found
                right = mid - 1;    // Look for a smaller ceiling
            }
        }
        return ceilingIndex;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 9, 14, 16, 18};
        int target = 10;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int index = searchCeiling(numbers, target);
        
        System.out.println("Ceiling of " + target + " is: " + numbers[index] + " (at index " + index + ")");
    }
}