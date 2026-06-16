import java.util.PriorityQueue;

/**
 * PROBLEM: Connect Ropes
 * * Given n ropes of different lengths, connect them into a single rope. The cost to connect two ropes 
 * is equal to the sum of their lengths. Find the minimum total cost to connect all ropes.
 * * Strategy: Min-Heap Greedy Optimization
 * To minimize cost, always pick the two shortest available ropes at each stage. Add their lengths, 
 * accumulate this value to the running total cost, and push the combined rope length back into a Min-Heap.
 * * Complexity:
 * Time Complexity: O(N log N)
 * Space Complexity: O(N)
 */
public class ConnectRopes {
    public static int minCostToConnect(int[] ropes) {
        if (ropes == null || ropes.length <= 1) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int absoluteAccumulationCost = 0;

        // Process until only a single combined rope element remains inside the heap structure
        while (minHeap.size() > 1) {
            int shortestRope1 = minHeap.poll();
            int shortestRope2 = minHeap.poll();
            
            int combinedLength = shortestRope1 + shortestRope2;
            absoluteAccumulationCost += combinedLength;
            
            minHeap.add(combinedLength);
        }
        return absoluteAccumulationCost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum rope connection cost total: " + minCostToConnect(ropes)); // 29
    }
}