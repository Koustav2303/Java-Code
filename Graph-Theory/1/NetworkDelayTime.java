import java.util.*;

public class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        Map<Integer, Integer> minDistance = new HashMap<>();
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], dist = curr[1];
            
            if (minDistance.containsKey(node)) continue;
            minDistance.put(node, dist);
            
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    if (!minDistance.containsKey(neighbor[0])) {
                        pq.add(new int[]{neighbor[0], dist + neighbor[1]});
                    }
                }
            }
        }
        
        if (minDistance.size() != n) return -1;
        int maxTime = 0;
        for (int time : minDistance.values()) maxTime = Math.max(maxTime, time);
        return maxTime;
    }

    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println("Network delay time: " + networkDelayTime(times, 4, 2)); // 2
    }
}