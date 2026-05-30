/**
 * PROBLEM: Majority Element
 * * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * * Constraint: Could you solve the problem in linear time and in O(1) space?
 * * Example:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * * Approach:
 * While a HashMap works in O(N) space, the Boyer-Moore Voting Algorithm solves it in O(1) space.
 * We maintain a 'count' and a 'candidate'. If count drops to 0, we pick a new candidate.
 * Since the majority element appears more than half the time, it will always survive the count decrements.
 */
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Majority Element: " + majorityElement(nums)); // 2
    }
}