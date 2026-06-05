/**
 * PROBLEM: Similar String Groups
 * * Two strings s1 and s2 are similar if we can swap at most two letters in s1 to make it equal to s2.
 * Given an array of strings strs where every string is an anagram of each other, group them into 
 * disjoint similarity clusters. Return the number of isolated string groups.
 * * Strategy: Complete Pairwise String Matching
 * Run a double nested loop to compare every string pair `strs[i]` and `strs[j]`. 
 * Two strings are similar if their character mismatch count is exactly 0 or 2. If valid, union their indices.
 * * Complexity:
 * Time Complexity: $O(N^2 \cdot M)$ where N is array size and M is individual string length.
 */
public class SimilarStringGroups {
    static class StringGroupDSU {
        int[] parent;
        int groups;
        public StringGroupDSU(int n) {
            parent = new int[n]; groups = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) { parent[rootP] = rootQ; groups--; }
        }
    }

    public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        StringGroupDSU dsu = new StringGroupDSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        return dsu.groups;
    }

    private static boolean isSimilar(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 2) return false; // Early pruning threshold breach
            }
        }
        return diff == 0 || diff == 2;
    }

    public static void main(String[] args) {
        String[] strs = {"tars", "rats", "arts", "star"};
        System.out.println("Total standalone string similarity groups: " + numSimilarGroups(strs)); // 2
    }
}