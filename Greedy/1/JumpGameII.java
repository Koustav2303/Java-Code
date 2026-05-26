public class JumpGameII {
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0; // Where the current jump can take us
        int farthest = 0;   // The farthest we can reach for the next jump
        
        // We stop at length - 1 because we don't need to jump from the last element
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            // If we have reached the end of the range for our current jump, we MUST jump again
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] jumps = {2, 3, 1, 1, 4};
        System.out.println("Minimum jumps to reach end: " + jump(jumps)); // Output: 2
    }
}