import java.util.Arrays;

public class FloorBinarySearch {
    public static int searchFloor(int[] arr, int target) {
        if (target < arr[0]) return -1; // No floor exists

        int left = 0, right = arr.length - 1;
        int floorIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Exact match is its own floor
            } else if (arr[mid] < target) {
                floorIndex = mid; // Potential floor found
                left = mid + 1;   // Look for a larger floor
            } else {
                right = mid - 1;
            }
        }
        return floorIndex;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 9, 14, 16, 18};
        int target = 12;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int index = searchFloor(numbers, target);
        
        System.out.println("Floor of " + target + " is: " + numbers[index] + " (at index " + index + ")");
    }
}