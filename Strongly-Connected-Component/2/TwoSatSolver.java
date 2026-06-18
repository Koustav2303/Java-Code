import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: 2-SAT Solver
 * * Solve the 2-Satisfiability problem: given a boolean formula in 2-CNF (Conjunctive Normal Form), 
 * determine if there exists a valid assignment of truth values to the variables that satisfies the formula.
 * * Strategy: Implication Graph Cycle Invariant
 * Model the 2-CNF formula as an implication directed graph where each clause $(A \lor B)$ is converted 
 * into two directed implication edges: $(\neg A \to B)$ and $(\neg B \to A)$. 
 * Decompose this implication graph into its SCCs using Tarjan's algorithm. 
 * The formula is satisfiable if and only if no variable $X$ and its negation $\neg X$ reside 
 * within the exact same Strongly Connected Component.
 */
public class TwoSatSolver {
    private int time = 0;

    public boolean solve2Sat(int numVariables, List<int[]> clauses) {
        // Implication graph sizes use double the variable count to account for negation states
        int graphNodesCount = 2 * numVariables;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < graphNodesCount; i++) adj.add(new ArrayList<>());

        // Node mapping index transformations: Variable i maps to 2*i, Negation i maps to 2*i + 1
        for (int[] clause : clauses) {
            int u = clause[0];
            int v = clause[1];

            int absU = Math.abs(u) - 1;
            int absV = Math.abs(v) - 1;

            int nodeU = (u > 0) ? 2 * absU : 2 * absU + 1;
            int nodeNotU = (u > 0) ? 2 * absU + 1 : 2 * absU;

            int nodeV = (v > 0) ? 2 * absV : 2 * absV + 1;
            int nodeNotV = (v > 0) ? 2 * absV + 1 : 2 * absV;

            // Insert implication edges: (NOT U -> V) and (NOT V -> U)
            adj.get(nodeNotU).add(nodeV);
            adj.get(nodeNotV).add(nodeU);
        }

        // Run Tarjan's algorithm to compute component mappings
        int[] disc = new int[graphNodesCount]; int[] low = new int[graphNodesCount]; int[] compMap = new int[graphNodesCount];
        boolean[] inStack = new boolean[graphNodesCount]; java.util.Stack<Integer> stack = new java.util.Stack<>();
        java.util.Arrays.fill(disc, -1);
        List<List<Integer>> sccs = new ArrayList<>();
        time = 0;

        for (int i = 0; i < graphNodesCount; i++) {
            if (disc[i] == -1) tarjan(i, disc, low, inStack, stack, compMap, sccs, adj);
        }

        // Verify if a variable and its negation reside in the same component
        for (int i = 0; i < numVariables; i++) {
            if (compMap[2 * i] == compMap[2 * i + 1]) {
                return false; // Unsatisfiable due to logical contradiction loop
            }
        }
        return true;
    }

    private void tarjan(int u, int[] disc, int[] low, boolean[] inStack, java.util.Stack<Integer> stack, int[] compMap, List<List<Integer>> sccs, List<List<Integer>> adj) {
        disc[u] = low[u] = ++time; stack.push(u); inStack[u] = true;
        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                tarjan(v, disc, low, inStack, stack, compMap, sccs, adj);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            int componentId = sccs.size();
            while (true) {
                int node = stack.pop(); inStack[node] = false;
                compMap[node] = componentId; component.add(node);
                if (node == u) break;
            }
            sccs.add(component);
        }
    }

    public static void main(String[] args) {
        List<int[]> clauses = new ArrayList<>();
        clauses.add(new int[]{1, 2});   // (X1 v X2)
        clauses.add(new int[]{-1, -2}); // (~X1 v ~X2)

        TwoSatSolver solver = new TwoSatSolver();
        System.out.println("Is 2-CNF formula satisfiable? " + solver.solve2Sat(2, clauses)); // true
    }
}