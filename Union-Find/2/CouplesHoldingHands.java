/**
 * PROBLEM: Couples Holding Hands
 * * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 * The people and seats are represented by an integer array row where row[i] is the ID of the 
 * person sitting in the ith seat. Couples are ordered sequentially: (0,1), (2,3), etc.
 * Return the minimum number of swaps so that every couple is sitting side by side.
 * * Strategy: Cycle Decomposition via Set Counting
 * Map couch positions to couple indices. Each group of size S that is tangled across couples 
 * creates a permutation cycle. The minimum swaps to resolve a cycle of size S is S - 1. 
 * Summing this across components gives: Minimum Swaps = Total Couples - Connected Components.
 */
public class CouplesHoldingHands {
    static class CoupleDSU {
        int[] parent;
        int count;
        public CoupleDSU(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
            }
        }
    }

    public static int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        CoupleDSU dsu = new CoupleDSU(n);

        for (int i = 0; i < row.length; i += 2) {
            int couple1 = row[i] / 2;
            int couple2 = row[i + 1] / 2;
            dsu.union(couple1, couple2);
        }
        
        return n - dsu.count;
    }

    public static void main(String[] args) {
        int[] row = {0, 2, 1, 3}; // Couple 0 (0,1) and Couple 1 (2,3) mixed up
        System.out.println("Minimum swaps needed: " + minSwapsCouples(row)); // 1
    }
}