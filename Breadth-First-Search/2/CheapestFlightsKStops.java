import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsKStops {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] f : flights) adj.get(f[0]).add(new int[]{f[1], f[2]});
        
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;
        
        Queue<int[]> queue = new LinkedList<>(); // {node, current_cost}
        queue.add(new int[]{src, 0});
        
        int stops = 0;
        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0], cost = curr[1];
                
                for (int[] neighbor : adj.get(node)) {
                    int nextNode = neighbor[0], price = neighbor[1];
                    // Only push if this path is strictly cheaper than what we found before
                    if (cost + price < minCost[nextNode]) {
                        minCost[nextNode] = cost + price;
                        queue.add(new int[]{nextNode, cost + price});
                    }
                }
            }
            stops++;
        }
        
        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }

    public static void main(String[] args) {
        int n = 3, src = 0, dst = 2, k = 1;
        int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};
        System.out.println("Cheapest flight: " + findCheapestPrice(n, flights, src, dst, k)); // 200
    }
}