import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * PROBLEM: Dijkstra's Algorithm
 * * Find the shortest path from a single source vertex to all other vertices in a directed graph 
 * with non-negative edge weights.
 * * Strategy: Min-Heap Vertex Cut Relaxation
 * Maintain a Min-Heap Priority Queue sorting nodes by distance. Extract the minimum distance node, 
 * evaluate its neighbors, and relax their weights, pushing minimized path adjustments back onto the heap.
 * * Complexity:
 * Time Complexity: O(E * log V)
 * Space Complexity: O(V + E)
 */
public class DijkstraAlgorithm {
    static class Node {
        int id, distance;
        Node(int id, int d) { this.id = id; this.distance = d; }
    }

    static class Edge {
        int target, weight;
        Edge(int t, int w) { this.target = t; this.weight = w; }
    }

    public static int[] computeShortestPaths(int vertices, List<List<Edge>> adj, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.distance > dist[curr.id]) continue; // Stale node optimization check

            for (Edge edge : adj.get(curr.id)) {
                if (dist[curr.id] + edge.weight < dist[edge.target]) {
                    dist[edge.target] = dist[curr.id] + edge.weight;
                    pq.add(new Node(edge.target, dist[edge.target]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int vertices = 3;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        adj.get(0).add(new Edge(1, 5));
        adj.get(1).add(new Edge(2, 2));
        adj.get(0).add(new Edge(2, 10));

        System.out.println("Shortest distances from 0: " + Arrays.toString(computeShortestPaths(vertices, adj, 0))); // [0, 5, 7]
    }
}