import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PROBLEM: Prim's Algorithm
 * * Compute the Minimum Spanning Tree (MST) of a connected, undirected graph using a vertex-expanding cut method.
 * * Strategy: Bounded Visited Cut Min-Heap
 * Start from an arbitrary root node (node 0). Track visited nodes. Maintain a Min-Heap tracking edges 
 * connecting visited nodes to unvisited nodes. Pop the minimum weight edge. If the target node is unvisited, 
 * add the edge to the MST, mark the node as visited, and push its outgoing edges into the heap.
 * * Complexity:
 * Time Complexity: O(E * log V)
 * Space Complexity: O(V + E)
 */
public class PrimsAlgorithm {
    static class Edge {
        int target, weight;
        Edge(int t, int w) { this.target = t; this.weight = w; }
    }

    static class HeapNode {
        int vertex, weight;
        HeapNode(int v, int w) { this.vertex = v; this.weight = w; }
    }

    public static int computeMstWeight(int vertices, List<List<Edge>> adj) {
        boolean[] inMst = new boolean[vertices];
        PriorityQueue<HeapNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        
        pq.add(new HeapNode(0, 0));
        int accumulatedMstWeightCost = 0;
        int nodesAddedCount = 0;

        while (!pq.isEmpty()) {
            HeapNode curr = pq.poll();
            if (inMst[curr.vertex]) continue;

            inMst[curr.vertex] = true;
            accumulatedMstWeightCost += curr.weight;
            nodesAddedCount++;
            if (nodesAddedCount == vertices) break;

            for (Edge edge : adj.get(curr.vertex)) {
                if (!inMst[edge.target]) {
                    pq.add(new HeapNode(edge.target, edge.weight));
                }
            }
        }
        return accumulatedMstWeightCost;
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(new Edge(1, 4)); adj.get(1).add(new Edge(0, 4));
        adj.get(1).add(new Edge(2, 1)); adj.get(2).add(new Edge(1, 1));
        adj.get(0).add(new Edge(2, 5)); adj.get(2).add(new Edge(0, 5));

        System.out.println("Prim's Minimum Spanning Tree baseline weight cost: " + computeMstWeight(vertices, adj)); // 5
    }
}