public class JumpGame {
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond our maximum reach, we're stuck
            if (i > maxReach) return false;
            
            // Greedily update the furthest we can reach
            maxReach = Math.max(maxReach, i + nums[i]);
            
            // If we can already reach the end, stop early
            if (maxReach >= nums.length - 1) return true;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] jumps = {2, 3, 1, 1, 4};
        System.out.println("Can reach the end? " + canJump(jumps));
    }
}