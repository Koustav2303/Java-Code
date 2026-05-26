public class KthMissingPositiveNumber {
    public static int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // The number of missing elements before index 'mid' is (arr[mid] - mid - 1)
            int missingCount = arr[mid] - mid - 1;
            
            if (missingCount < k) {
                low = mid + 1; // We need to look further right
            } else {
                high = mid - 1; // Look left
            }
        }
        
        // The Kth missing number sits between 'high' and 'low'
        // Missing formula simplifies to: low + k
        return low + k;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        System.out.println("The " + k + "th missing positive is: " + findKthPositive(arr, k)); // 9
    }
}