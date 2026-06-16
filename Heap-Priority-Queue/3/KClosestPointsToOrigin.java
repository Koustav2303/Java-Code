import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * PROBLEM: K Closest Points to Origin
 * * Given an array of points where points[i] = [x, y] represents a point on the X-Y plane and an integer k, 
 * return the k closest points to the origin (0, 0).
 * * Strategy: Bounded Distance Max-Heap
 * Maintain a Max-Heap tracking points ordered by their Euclidean distance squared: `(x^2 + y^2)`. 
 * Keep the heap size bounded to at most `k`. When a new point arrives, if its distance is smaller than the 
 * heap's peak element, pop the peak and insert the new point.
 * * Complexity:
 * Time Complexity: O(N log K)
 * Space Complexity: O(K)
 */
public class KClosestPointsToOrigin {
    public static int[][] kClosest(int[][] points, int k) {
        // Max-heap sorting distance calculations descending
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare((b[0]*b[0] + b[1]*b[1]), (a[0]*a[0] + a[1]*a[1]))
        );

        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Evict furthest point once size exceeds k
            }
        }

        int[][] result = new int[k][2];
        while (k > 0) {
            result[--k] = maxHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}};
        System.out.println("Closest point layout array: " + Arrays.deepToString(kClosest(points, 1))); // [[-2,2]]
    }
}