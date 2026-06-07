/**
 * PROBLEM: Minimum Moves to Equal Array Elements
 * * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment n - 1 elements of the array by 1.
 * * Strategy: Inverted Decrement Equivalency
 * Mathematically, incrementing $n - 1$ elements by 1 is relative equivalent to *decrementing* a single element 
 * by 1. Therefore, our target is to reduce every element down to match the absolute minimum value in the array. 
 * The total moves required is the sum of the differences between each element and the array's minimum value:
 * $$ \text{Total Moves} = \sum (nums[i] - \text{minValue}) = \sum nums[i] - n \cdot \text{minValue} $$
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class MinimumMovesToEqualArrayElements {
    public static int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        long sum = 0;

        for (int num : nums) {
            sum += num;
            if (num < min) min = num;
        }

        return (int) (sum - (long) nums.length * min);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Minimum moves required: " + minMoves(nums)); // 3 -> [1,2,3] -> [2,3,3] -> [3,4,3] -> [4,4,4]
    }
}