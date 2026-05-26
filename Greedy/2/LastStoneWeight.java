import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static int lastStoneWeight(int[] stones) {
        // Pass Collections.reverseOrder() to create a Max-Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll(); // Heaviest
            int stone2 = maxHeap.poll(); // Second heaviest

            if (stone1 != stone2) {
                maxHeap.add(stone1 - stone2); // Put the smashed remainder back
            }
        }

        // Return the last stone, or 0 if they all destroyed each other
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        System.out.println("Stones: " + Arrays.toString(stones));
        System.out.println("Weight of the last stone: " + lastStoneWeight(stones));
    }
}