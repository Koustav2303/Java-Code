import java.util.Arrays;

/**
 * PROBLEM: Partition to K Equal Sum Subsets
 * * Given an integer array nums and an integer k, return true if it is possible to divide this array 
 * into k non-empty subsets whose sums are all equal.
 * * Strategy: Reverse-Sorted Target Bucket Filling
 * Sort the array to process larger elements first. Maintain an array of k bucket capacities. Try to place 
 * each element into each bucket sequentially, pruning whenever a bucket's capacity exceeds the target subset sum.
 */
public class PartitionKSubsets {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        Arrays.sort(nums); // Sort to allow optimal greedy processing backward
        int[] subsets = new int[k];
        
        return backtrack(nums.length - 1, nums, subsets, target);
    }

    private static boolean backtrack(int index, int[] nums, int[] subsets, int target) {
        if (index < 0) return true; // All elements distributed successfully

        int currentElement = nums[index];
        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] + currentElement <= target) {
                subsets[i] += currentElement;
                if (backtrack(index - 1, nums, subsets, target)) return true;
                subsets[i] -= currentElement; // Backtrack
            }
            // Sibling Optimization Pruning: If an element fails in an empty bucket, 
            // placing it in another empty identical bucket will also fail. Break early.
            if (subsets[i] == 0) break;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        System.out.println("Can partition into 4 equal sets? " + canPartitionKSubsets(nums, 4)); // true
    }
}