import java.util.*;

public class MinCostConnectPoints {
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
        boolean[] inMST = new boolean[n];
        
        minHeap.add(new int[]{0, 0});
        int totalCost = 0, edgesUsed = 0;
        
        while (edgesUsed < n) {
            int[] curr = minHeap.poll();
            int node = curr[0], cost = curr[1];
            
            if (inMST[node]) continue;
            
            inMST[node] = true;
            totalCost += cost;
            edgesUsed++;
            
            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (!inMST[nextNode]) {
                    int dist = Math.abs(points[node][0] - points[nextNode][0]) + 
                               Math.abs(points[node][1] - points[nextNode][1]);
                    minHeap.add(new int[]{nextNode, dist});
                }
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Min cost to connect points: " + minCostConnectPoints(points)); // 20
    }
}