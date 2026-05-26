public class MinMovesEqualElements {
    public static int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        
        int totalMoves = 0;
        // The total moves is the sum of the difference between each element and the minimum
        for (int num : nums) {
            totalMoves += num - min;
        }
        
        return totalMoves;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3}; 
        // Move 1: [2, 3, 3] -> Move 2: [3, 4, 3] -> Move 3: [4, 4, 4]
        System.out.println("Minimum moves to make elements equal: " + minMoves(nums));
    }
}