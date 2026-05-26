import java.util.Arrays;

public class MutatedArrayClosestTarget {
    public static int findBestValue(int[] arr, int target) {
        int low = 0, high = 0;
        for (int num : arr) high = Math.max(high, num);
        
        int bestValue = 0;
        int minDiff = Integer.MAX_VALUE;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = calculateSum(arr, mid);
            
            int diff = Math.abs(sum - target);
            
            // If strictly closer, OR equally close but a smaller value
            if (diff < minDiff || (diff == minDiff && mid < bestValue)) {
                minDiff = diff;
                bestValue = mid;
            }
            
            if (sum == target) {
                return mid; // Exact match found
            } else if (sum < target) {
                low = mid + 1; // Increase cap to get closer to target
            } else {
                high = mid - 1; // Decrease cap to shrink sum
            }
        }
        return bestValue;
    }
    
    private static int calculateSum(int[] arr, int cap) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, cap);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {4, 9, 3};
        int target = 10;
        System.out.println("Best value to replace elements: " + findBestValue(arr, target)); // 3
    }
}