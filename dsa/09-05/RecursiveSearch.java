public class RecursiveSearch {
    public static void main(String[] args) {
        int[] sortedArray = {10, 22, 35, 47, 50, 63, 75, 88, 91};
        int target = 63;
        
        int result = binarySearch(sortedArray, 0, sortedArray.length - 1, target);
        
        if (result == -1) {
            System.out.println("Element not present in array.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }

    /**
     * Recursive Binary Search Logic
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to recursion stack
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            // If element is present at the middle
            if (arr[mid] == target) {
                return mid;
            }

            // If element is smaller than mid, search the left subarray
            if (arr[mid] > target) {
                return binarySearch(arr, left, mid - 1, target);
            }

            // Else search the right subarray
            return binarySearch(arr, mid + 1, right, target);
        }

        // Element is not present
        return -1;
    }
}