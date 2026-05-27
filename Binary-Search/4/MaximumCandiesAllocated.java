public class MaximumCandiesAllocated {
    public static int maximumCandies(int[] candies, long k) {
        int low = 1, high = 0;
        for (int candy : candies) high = Math.max(high, candy);
        
        int best = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canAllocate(candies, k, mid)) {
                best = mid;    // This allocation works, try for more
                low = mid + 1; 
            } else {
                high = mid - 1; // Too many candies per child, reduce
            }
        }
        return best;
    }
    
    private static boolean canAllocate(int[] candies, long k, int amount) {
        long childrenFed = 0;
        for (int candy : candies) {
            childrenFed += (candy / amount);
        }
        return childrenFed >= k;
    }

    public static void main(String[] args) {
        int[] candies = {5, 8, 6};
        long k = 3;
        System.out.println("Max candies per child: " + maximumCandies(candies, k)); // 5
    }
}