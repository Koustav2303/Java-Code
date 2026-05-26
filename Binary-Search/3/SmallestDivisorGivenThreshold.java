public class SmallestDivisorGivenThreshold {
    public static int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 1000000;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (computeSum(nums, mid) <= threshold) {
                high = mid; // Try a smaller divisor
            } else {
                low = mid + 1; // Sum too high, need a larger divisor
            }
        }
        return low;
    }
    
    private static int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            // Equivalent to Math.ceil((double)num / divisor) but purely integer math
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;
        System.out.println("Smallest divisor: " + smallestDivisor(nums, threshold)); // 5
    }
}