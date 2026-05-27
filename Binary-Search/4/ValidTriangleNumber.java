import java.util.Arrays;

public class ValidTriangleNumber {
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int target = nums[i] + nums[j];
                int low = j + 1, high = nums.length - 1;
                int k = j;
                
                // Binary search for the largest index k where nums[k] < target
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[mid] < target) {
                        k = mid;
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                count += k - j;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};
        System.out.println("Valid triangle combinations: " + triangleNumber(nums)); // 3
    }
}