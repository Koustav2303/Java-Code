import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Number of Boomerangs
 * * You are given n points in the plane that are all distinct, where points[i] = [xi, yi].
 * A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals 
 * the distance between i and k (the order of the tuple matters).
 * Return the number of boomerangs.
 * * Example:
 * Input: points = [[0,0],[1,0],[2,0]]
 * Output: 2
 * Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
 * * Approach:
 * For every point 'i', count the frequencies of distances to all other points 'j' using a HashMap.
 * If 'k' points have the same distance to 'i', we can pick any 2 of them to form a boomerang 
 * in (k) * (k - 1) ways.
 */
public class NumberOfBoomerangs {
    public static int numberOfBoomerangs(int[][] points) {
        int boomerangs = 0;
        Map<Integer, Integer> distanceCount = new HashMap<>();
        
        for (int i = 0; i < points.length; i++) {
            // For every point, count how many other points sit at a specific distance
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                
                int dist = getDistance(points[i], points[j]);
                distanceCount.put(dist, distanceCount.getOrDefault(dist, 0) + 1);
            }
            
            // Calculate combinations
            for (int count : distanceCount.values()) {
                // Permutation formula: P(count, 2) = count * (count - 1)
                boomerangs += count * (count - 1);
            }
            distanceCount.clear(); // Clear for the next anchor point
        }
        
        return boomerangs;
    }
    
    // We omit the square root to avoid double precision issues. Comparing squared distance is perfectly fine.
    private static int getDistance(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println("Total Boomerangs: " + numberOfBoomerangs(points)); // 2
    }
}