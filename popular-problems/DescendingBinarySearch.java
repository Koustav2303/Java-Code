import java.util.Arrays;

public class DescendingBinarySearch {
    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;
            
            // Logic is flipped for descending order
            if (arr[mid] > target) {
                left = mid + 1; // Go right
            } else {
                right = mid - 1; // Go left
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {99, 85, 72, 64, 55, 41, 30, 15, 8, 2};
        int target = 41;
        
        System.out.println("Descending Array: " + Arrays.toString(numbers));
        int index = search(numbers, target);
        
        System.out.println("Target " + target + " found at index: " + index);
    }
}