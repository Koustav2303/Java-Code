/**
 * PROBLEM: K Closest Points to Origin
 * * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane 
 * and an integer k, return the k closest points to the origin (0, 0).
 * * Example:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * * Approach:
 * Maintain a Max-Heap of size K. The heap compares points by their squared Euclidean distance 
 * from the origin. If the heap exceeds size K, we evict the point FURTHEST from the origin.
 * The remaining K points are our answer.
 */
import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPoints {
    public static int[][] kClosest(int[][] points, int k) {
        // Max-Heap: Compare points by distance, descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distB, distA);
        });
        
        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the furthest point
            }
        }
        
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        int[][] res = kClosest(points, k);
        System.out.print("Closest points: ");
        for (int[] p : res) System.out.print(Arrays.toString(p) + " ");
        System.out.println();
    }
}