import java.util.*;

/**
 * PROBLEM: Smallest String With Swaps
 * * You are given a string s, and an array of pairs of indices where pairs[i] = [a, b] 
 * indicates you can swap the characters at indices a and b of the string any number of times.
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 * * Strategy: Disjoint Index Character Grouping
 * Indices that can be swapped form connected components. Map out these components using Union-Find. 
 * Group indices by their root values, extract their matching characters, sort them in descending order 
 * (to pop elements in O(1)), and rebuild the finalized lexicographically sorted target string.
 */
public class SmallestStringSwaps {
    static class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        DSU dsu = new DSU(n);

        for (List<Integer> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }

        // Group matching characters by their absolute DSU root components
        Map<Integer, List<Character>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(s.charAt(i));
        }

        // Sort each cluster list in reverse order for efficient stack-like extraction
        for (List<Character> list : components.values()) {
            list.sort(Collections.reverseOrder());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            List<Character> list = components.get(root);
            sb.append(list.remove(list.size() - 1)); // O(1) removal from end
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<List<Integer>> pairs = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2));
        System.out.println("Lexicographically optimized string: " + smallestStringWithSwaps("dcba", pairs)); // "abcd"
    }
}