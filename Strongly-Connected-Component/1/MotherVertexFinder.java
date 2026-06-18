import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Mother Vertex Finder
 * * Find a "mother vertex" in a directed graph—a vertex from which all other vertices can be reached via directed paths.
 * * Strategy: Topological Finish Time Check
 * The vertex with the latest finishing time in a global DFS search is guaranteed to be a member of the leading 
 * source component in the condensation DAG. Run a standard DFS pass over the entire graph, tracking the last node 
 * to finish processing. Reset the tracking arrays and run a second DFS from this node. 
 * If it visits all vertices, it is a valid mother vertex; otherwise, no mother vertex exists.
 */
public class MotherVertexFinder {
    public static int findMotherVertex(int vertices, List<List<Integer>> adj) {
        boolean[] visited = new boolean[vertices];
        int potentialMotherCandidate = -1;

        // Pass 1: Identify the component candidate with the latest finishing time
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsTrace(i, visited, adj);
                potentialMotherCandidate = i;
            }
        }

        // Pass 2: Verify if the candidate can reach all other vertices
        Arrays.fill(visited, false);
        dfsTrace(potentialMotherCandidate, visited, adj);

        for (boolean check : visited) {
            if (!check) return -1; // Missing full reachability bounds
        }
        return potentialMotherCandidate;
    }

    private static void dfsTrace(int u, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) dfsTrace(v, visited, adj);
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1); adj.get(0).add(2);
        adj.get(1).add(3); adj.get(4).add(1); // Vertex 0 can reach everything except 4; no global mother exists

        System.out.println("Mother Vertex coordinate target: " + findMotherVertex(vertices, adj)); // -1
    }
}