/**
 * PROBLEM: Satisfiability of Equality Equations
 * * You are given an array of strings equations that represent relationships between variables 
 * where each string equations[i] is of length 4 and takes one of two different forms: 
 * "a==b" or "a!=b". Here, a and b are lowercase letters.
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations.
 * * Approach:
 * Two-pass Union-Find.
 * Pass 1: Process all "==" equations and unify the characters into disjoint sets.
 * Pass 2: Process all "!=" equations and check if the characters share the same parent root. 
 * If they do, they are fundamentally equal, which creates a logical contradiction.
 */
public class EqualityEquations {
    static class UnionFind {
        int[] parent = new int[26];
        public UnionFind() {
            for (int i = 0; i < 26; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public static boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind();
        
        // Pass 1: Build equivalence classes
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                uf.union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }
        
        // Pass 2: Check for contradictions
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                if (uf.find(eq.charAt(0) - 'a') == uf.find(eq.charAt(3) - 'a')) {
                    return false; // Contradiction!
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] equations = {"a==b", "b!=c", "c==a"};
        System.out.println("Are equations valid? " + equationsPossible(equations)); // false
    }
}