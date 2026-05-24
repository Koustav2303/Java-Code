import java.util.Arrays;

public class HouseRobberII {
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(
            robHelper(nums, 0, nums.length - 2), 
            robHelper(nums, 1, nums.length - 1)
        );
    }

    private static int robHelper(int[] nums, int start, int end) {
        int rob1 = 0, rob2 = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(nums[i] + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    public static void main(String[] args) {
        int[] houses = {2, 3, 2}; // Circle: 2 and 2 are adjacent
        System.out.println("Circular Houses: " + Arrays.toString(houses));
        System.out.println("Max loot: " + rob(houses));
    }
}