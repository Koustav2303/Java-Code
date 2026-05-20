import java.util.Arrays;

public class RotatedArrayBinarySearch {
    public static int searchRotated(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;

            // Check if the left half is strictly sorted
            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1; // Target is in the left half
                } else {
                    left = mid + 1; // Target is in the right half
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1; // Target is in the right half
                } else {
                    right = mid - 1; // Target is in the left half
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {6, 7, 8, 9, 1, 2, 3, 4, 5};
        int target = 2;
        
        System.out.println("Rotated Array: " + Arrays.toString(numbers));
        int index = searchRotated(numbers, target);
        
        System.out.println("Target " + target + " found at index: " + index);
    }
}