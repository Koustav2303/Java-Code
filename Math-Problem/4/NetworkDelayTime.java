import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // PriorityQueue to sort by the accumulated time: [Node, TimeFromStart]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        
        boolean[] visited = new boolean[n + 1];
        int maxTime = 0;
        int nodesVisited = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int time = current[1];

            if (visited[node]) continue;
            visited[node] = true;
            
            maxTime = time;
            nodesVisited++;

            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    if (!visited[neighbor[0]]) {
                        pq.add(new int[]{neighbor[0], time + neighbor[1]});
                    }
                }
            }
        }
        return nodesVisited == n ? maxTime : -1;
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,1}, {2,3,1}, {3,4,1}};
        int n = 4, k = 2;
        System.out.println("Time to reach all nodes: " + networkDelayTime(times, n, k));
    }
}