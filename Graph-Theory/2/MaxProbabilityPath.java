import java.util.*;

/**
 * PROBLEM: Path with Maximum Probability
 * * You are given an undirected weighted graph of n nodes (0-indexed), represented by a list of edges 
 * where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of 
 * success of traversing that edge succProb[i].
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end.
 * * Strategy:
 * Variation of Dijkstra's Algorithm. Instead of tracking the absolute minimum sum, we track the 
 * absolute **maximum multiplication product** using a Max-Heap (PriorityQueue sorting weights descending).
 * * Complexity:
 * Time Complexity: O(E log V)
 * Space Complexity: O(V + E)
 */
public class MaxProbabilityPath {
    static class State {
        int node;
        double prob;
        State(int n, double p) { node = n; prob = p; }
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<State>> adj = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            adj.computeIfAbsent(edges[i][0], x -> new ArrayList<>()).add(new State(edges[i][1], succProb[i]));
            adj.computeIfAbsent(edges[i][1], x -> new ArrayList<>()).add(new State(edges[i][0], succProb[i]));
        }
        
        double[] maxProb = new double[n];
        maxProb[start] = 1.0;
        
        // Sorting via a Max-Heap based on custom probability states
        PriorityQueue<State> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        maxHeap.add(new State(start, 1.0));
        
        while (!maxHeap.isEmpty()) {
            State curr = maxHeap.poll();
            if (curr.node == end) return curr.prob;
            if (curr.prob < maxProb[curr.node]) continue; // Stale path verification
            
            if (adj.containsKey(curr.node)) {
                for (State neighbor : adj.get(curr.node)) {
                    double continuousProb = curr.prob * neighbor.prob;
                    if (continuousProb > maxProb[neighbor.node]) {
                        maxProb[neighbor.node] = continuousProb;
                        maxHeap.add(new State(neighbor.node, continuousProb));
                    }
                }
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.2};
        System.out.println("Maximum success path probability: " + maxProbability(3, edges, succProb, 0, 2)); // 0.25 (0->1->2)
    }
}