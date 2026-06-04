import java.util.*;

/**
 * PROBLEM: Most Stones Removed with Same Row or Column
 * * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * Given an array stones, return the largest possible number of stones that can be removed.
 * * Strategy: Bipartite Projection Components Identification
 * Max Stones Removed = Total Stones - Total Connected Components.
 * Map rows and columns to a unified 1D space by adding an offset to the column index (e.g., column + 10001). 
 * Union the row node with the offset column node for every stone location.
 */
public class MostStonesRemovedDSU {
    static class SimpleDSU {
        Map<Integer, Integer> parent = new HashMap<>();
        int components = 0;

        public int find(int i) {
            if (!parent.containsKey(i)) {
                parent.put(i, i);
                components++;
            }
            if (parent.get(i) == i) return i;
            parent.put(i, find(parent.get(i)));
            return parent.get(i);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent.put(rootP, rootQ);
                components--; // Two sub-components merge into one
            }
        }
    }

    public static int removeStones(int[][] stones) {
        SimpleDSU dsu = new SimpleDSU();
        for (int[] stone : stones) {
            // Project column index safely out of bounds of standard row ranges via fixed scalar padding
            dsu.union(stone[0], stone[1] + 10001);
        }
        return stones.length - dsu.components;
    }

    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println("Maximum safely extractable stones count: " + removeStones(stones)); // 5
    }
}