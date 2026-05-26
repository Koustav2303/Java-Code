import java.util.Arrays;
import java.util.PriorityQueue;

public class ConnectRopes {
    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int totalCost = 0;

        // Keep combining until 1 mega-rope is left
        while (minHeap.size() > 1) {
            int rope1 = minHeap.poll();
            int rope2 = minHeap.poll();
            
            int cost = rope1 + rope2;
            totalCost += cost;
            
            // Put the newly combined rope back into the pile
            minHeap.add(cost);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Ropes: " + Arrays.toString(ropes));
        System.out.println("Minimum cost to connect all: " + minCost(ropes)); 
        // Output: 29 (2+3=5. 4+5=9. 6+9=15. Total = 5+9+15 = 29)
    }
}