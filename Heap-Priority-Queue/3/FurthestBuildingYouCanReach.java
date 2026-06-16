import java.util.PriorityQueue;

/**
 * PROBLEM: Furthest Building You Can Reach
 * * Given an array heights representing building elevations, an integer bricks, and an integer ladders,
 * return the furthest building index you can reach traveling left-to-right using resources optimally.
 * * Strategy: Ladder Allocation Min-Heap
 * Greedily prioritize using ladders for the largest climbs. Use a Min-Heap to track the size of climbs 
 * where a ladder was used. When the number of climbs exceeds available ladders, pop the smallest climb from 
 * the heap and substitute bricks to cover that distance. If bricks run out, return the current building index.
 * * Complexity:
 * Time Complexity: O(N log L) where L is total ladders.
 * Space Complexity: O(L) matching max ladders allocation boundaries.
 */
public class FurthestBuildingYouCanReach {
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladderClimbsHeap = new PriorityQueue<>(); // Track smallest ladder jumps

        for (int i = 0; i < heights.length - 1; i++) {
            int climbDistance = heights[i + 1] - heights[i];
            
            if (climbDistance > 0) {
                ladderClimbsHeap.add(climbDistance);
                
                // If ladders capacity limit overflows, substitute bricks for the smallest climb
                if (ladderClimbsHeap.size() > ladders) {
                    bricks -= ladderClimbsHeap.poll();
                }
                
                // If bricks bank underflows below zero, the journey is blocked
                if (bricks < 0) {
                    return i;
                }
            }
        }
        return heights.length - 1;
    }

    public static void main(String[] args) {
        int[] heights = {4, 2, 7, 6, 9, 14, 12};
        System.out.println("Furthest achievable building node: " + furthestBuilding(heights, 5, 1)); // 4
    }
}