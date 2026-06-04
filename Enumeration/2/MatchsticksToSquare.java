import java.util.Arrays;

/**
 * PROBLEM: Matchsticks to Square
 * * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. 
 * You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, 
 * and each matchstick must be used exactly one time.
 * Return true if you can make this square and false otherwise.
 * * Strategy: Reverse-Sorted Greedy Bucket Search
 * Calculate the total perimeter sum. If sum % 4 != 0, a square is impossible. Sort the matchsticks descending 
 * to handle large values first, then try to place each stick into one of four bucket sides.
 */
public class MatchsticksToSquare {
    public static boolean makesquare(int[] matchsticks) {
        int totalSum = 0;
        for (int match : matchsticks) totalSum += match;
        if (totalSum % 4 != 0 || matchsticks.length < 4) return false;

        int sideLength = totalSum / 4;
        // Sort ascending, then parse backward to simulate descending search order
        Arrays.sort(matchsticks);
        int[] sides = new int[4];
        
        return backtrack(matchsticks.length - 1, matchsticks, sides, sideLength);
    }

    private static boolean backtrack(int index, int[] matchsticks, int[] sides, int target) {
        if (index < 0) {
            // If all elements are exhausted, verify if all four side capacities match target bounds
            return sides[0] == target && sides[1] == target && sides[2] == target;
        }

        int currentStick = matchsticks[index];
        for (int i = 0; i < 4; i++) {
            if (sides[i] + currentStick <= target) {
                sides[i] += currentStick;
                if (backtrack(index - 1, matchsticks, sides, target)) return true;
                sides[i] -= currentStick; // Backtrack step
            }
            // Optimization Pruning: If a stick fits perfectly but downstream choices fail, 
            // moving it to an empty duplicate sibling bucket yields identical results. Break early.
            if (sides[i] == 0) break;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] matchsticks = {1, 1, 2, 2, 2};
        System.out.println("Can form square? " + makesquare(matchsticks)); // true (Sides: 2, 2, 2, 1+1)
    }
}