public class MinLimitBallsInBag {
    public static int minimumSize(int[] nums, int maxOperations) {
        int low = 1, high = 0;
        for (int num : nums) high = Math.max(high, num);
        
        while (low < high) {
            int midPenalty = low + (high - low) / 2;
            
            if (canDivide(nums, maxOperations, midPenalty)) {
                high = midPenalty; // Try to get an even lower penalty
            } else {
                low = midPenalty + 1; // Penalty too strict, relax it
            }
        }
        return low;
    }
    
    private static boolean canDivide(int[] nums, int maxOperations, int penalty) {
        int ops = 0;
        for (int num : nums) {
            // (num - 1) / penalty gives the number of splits required
            ops += (num - 1) / penalty;
        }
        return ops <= maxOperations;
    }

    public static void main(String[] args) {
        int[] nums = {9};
        int maxOperations = 2; // Split 9 -> 3,3,3 (penalty is 3)
        System.out.println("Minimum penalty limit: " + minimumSize(nums, maxOperations)); // 3
    }
}