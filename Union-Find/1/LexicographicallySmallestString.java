/**
 * PROBLEM: Lexicographically Smallest Equivalent String
 * * You are given two strings s1 and s2 of the same length and a string baseStr.
 * We say s1[i] and s2[i] are equivalent characters. You can form equivalent classes out of them.
 * Return the lexicographically smallest string of baseStr by using the equivalency information.
 * * Strategy: Ordered Lexical Parent Anchoring
 * When uniting character equivalency classes, always maintain alphabetical order inside the root mapping step: 
 * ensure the smaller character becomes the absolute parent root of the larger one.
 */
public class LexicographicallySmallestString {
    static class LexicalDSU {
        int[] parent = new int[26];
        public LexicalDSU() {
            for (int i = 0; i < 26; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                // Enforce constraint rule: Smaller character becomes the root node choice
                if (rootP < rootQ) parent[rootQ] = rootP;
                else parent[rootP] = rootQ;
            }
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        LexicalDSU dsu = new LexicalDSU();
        for (int i = 0; i < s1.length(); i++) {
            dsu.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            int smallestRoot = dsu.find(c - 'a');
            sb.append((char) (smallestRoot + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Smallest String mapping: " + smallestEquivalentString("parker", "morris", "parser")); // "makkek"
    }
}