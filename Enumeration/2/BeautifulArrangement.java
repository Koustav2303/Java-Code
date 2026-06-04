/**
 * PROBLEM: Beautiful Arrangement
 * * Suppose you have n integers labeled 1 to n. A permutation of those n integers is considered a beautiful arrangement 
 * if for every i (1 <= i <= n), either of the following is true:
 * 1. perm[i] is divisible by i.
 * 2. i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 * * Strategy: Early Position Pruning Match
 * Instead of computing a full permutation and then checking validity, place elements at index position sequentially, 
 * verifying divisibility properties *on the fly*. If an element doesn't fit the current slot index, prune that entire branch.
 */
public class BeautifulArrangement {
    private static int count = 0;

    public static int countArrangement(int n) {
        count = 0;
        boolean[] visited = new boolean[n + 1];
        backtrack(1, n, visited);
        return count;
    }

    private static void backtrack(int pos, int n, boolean[] visited) {
        if (pos > n) {
            count++;
            return;
        }

        for (int val = 1; val <= n; val++) {
            // Apply property check filters dynamically before taking a decision branch path
            if (!visited[val] && (val % pos == 0 || pos % val == 0)) {
                visited[val] = true;
                backtrack(pos + 1, n, visited);
                visited[val] = false; // Reset mask
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Beautiful Arrangements for n = 3: " + countArrangement(3)); // 3 solutions
    }
}