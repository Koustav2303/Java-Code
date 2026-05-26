public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // If the element to the right is greater, we must climb the hill to the right
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } 
            // Otherwise, we climb to the left (or we are currently on a peak)
            else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("A peak element is at index: " + findPeakElement(nums)); // 5 (value 6)
    }
}