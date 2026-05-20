import java.util.Arrays;

public class ClosestElementBinarySearch {
    public static int searchClosest(int[] arr, int target) {
        if (target <= arr[0]) return 0;
        if (target >= arr[arr.length - 1]) return arr.length - 1;

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // At this point, left and right surround the target.
        // Compare the distances to see which is closer.
        if ((arr[left] - target) < (target - arr[right])) {
            return left;
        } else {
            return right;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        int target = 32;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int index = searchClosest(numbers, target);
        
        System.out.println("Element closest to " + target + " is: " + numbers[index]);
    }
}