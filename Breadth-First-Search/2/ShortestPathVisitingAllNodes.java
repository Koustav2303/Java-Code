import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    public static int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n == 1) return 0;
        
        int targetBitmask = (1 << n) - 1;
        Queue<int[]> queue = new LinkedList<>(); // {currentNode, visitedBitmask, steps}
        boolean[][] visited = new boolean[n][1 << n];
        
        // Start BFS from every node simultaneously
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 1 << i, 0});
            visited[i][1 << i] = true;
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], bitmask = curr[1], steps = curr[2];
            
            if (bitmask == targetBitmask) return steps;
            
            for (int neighbor : graph[node]) {
                int nextBitmask = bitmask | (1 << neighbor);
                
                if (!visited[neighbor][nextBitmask]) {
                    visited[neighbor][nextBitmask] = true;
                    queue.add(new int[]{neighbor, nextBitmask, steps + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2,3}, {0}, {0}, {0}};
        System.out.println("Shortest path visiting all nodes: " + shortestPathLength(graph)); // 4
    }
}