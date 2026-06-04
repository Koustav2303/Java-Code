import java.util.*;

/**
 * PROBLEM: Evaluate Division
 * * You are given an array of variable pairs equations and an array of real numbers values, 
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable. Given queries, return the answers.
 * * Strategy: Weighted Disjoint Set Union
 * Maintain a typical parent lookup map along with a companion `weightMap`, where weightMap.get(X) 
 * stores the evaluated ratio value of X / Parent(X). During path compression, scale child weights 
 * transitively by multiplying them by their parents' ratios.
 */
public class EvaluateDivisionDSU {
    static class WeightedDSU {
        Map<String, String> parent = new HashMap<>();
        Map<String, Double> weight = new HashMap<>();

        public void add(String x) {
            if (parent.containsKey(x)) return;
            parent.put(x, x);
            weight.put(x, 1.0);
        }

        public String find(String x) {
            if (!parent.containsKey(x)) return null;
            String root = parent.get(x);
            if (!root.equals(x)) {
                String originalParent = root;
                String absoluteRoot = find(originalParent);
                parent.put(x, absoluteRoot);
                weight.put(x, weight.get(x) * weight.get(originalParent)); // Chain multiplication rule
            }
            return parent.get(x);
        }

        public void union(String x, String y, double val) {
            add(x); add(y);
            String rootX = find(x);
            String rootY = find(y);
            if (!rootX.equals(rootY)) {
                parent.put(rootX, rootY);
                // Math conversion calculation: x/y = val => (rootX * weightX) / (rootY * weightY) = val
                weight.put(rootX, (val * weight.get(y)) / weight.get(x));
            }
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        WeightedDSU dsu = new WeightedDSU();
        for (int i = 0; i < values.length; i++) {
            dsu.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String q1 = queries.get(i).get(0);
            String q2 = queries.get(i).get(1);

            String root1 = dsu.find(q1);
            String root2 = dsu.find(q2);

            if (root1 == null || root2 == null || !root1.equals(root2)) {
                results[i] = -1.0;
            } else {
                results[i] = dsu.weight.get(q1) / dsu.weight.get(q2);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<List<String>> eq = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] vals = {2.0, 3.0}; // a/b=2, b/c=3 => a/c=6
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"));
        System.out.println("Division query evaluations: " + Arrays.toString(calcEquation(eq, vals, queries))); // [6.0, 0.5]
    }
}