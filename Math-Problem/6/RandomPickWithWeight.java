import java.util.Random;

/**
 * PROBLEM: Random Pick with Weight
 * * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * Implement the pickIndex function, which randomly picks an index in the range [0, w.length - 1] 
 * proportional to its weight probability distribution.
 * * Strategy: Bounded Cumulative Range Binary Search
 * Generate a prefix sum array of the weights. The total weight sum defines our sampling range upper bound. 
 * Generate a random integer within this range: `target = rand.nextInt(totalSum) + 1`. 
 * Use binary search on the prefix sum array to find the smallest index where the cumulative sum is $\ge$ our target.
 */
public class RandomPickWithWeight {
    private final int[] prefixSums;
    private final int totalSum;
    private final Random rand = new Random();

    public RandomPickWithWeight(int[] w) {
        this.prefixSums = new int[w.length];
        int runningSum = 0;
        for (int i = 0; i < w.length; i++) {
            runningSum += w[i];
            this.prefixSums[i] = runningSum;
        }
        this.totalSum = runningSum;
    }
    
    public int pickIndex() {
        int target = rand.nextInt(totalSum) + 1; // Sample range bounds [1, totalSum]
        
        // Binary search lower bound search
        int low = 0;
        int high = prefixSums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prefixSums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] weights = {1, 3}; // Index 0 has 25% chance, Index 1 has 75% chance
        RandomPickWithWeight picker = new RandomPickWithWeight(weights);
        System.out.println("Random sample selection index draw: " + picker.pickIndex());
    }
}