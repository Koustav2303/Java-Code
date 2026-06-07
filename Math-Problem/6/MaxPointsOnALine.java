import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Max Points on a Line
 * * Given an array of points where points[i] = [xi, yi] represents a coordinate point on a 2D plane, 
 * return the maximum number of points that lie on the same straight line.
 * * Strategy: Irreducible Fractional Slopes Map
 * Loop through each point. For every subsequent point, calculate its directional slope vector `(dy, dx)`. 
 * To avoid precision issues from floating-point division, divide both coordinate deltas by their GCD to find 
 * the irreducible slope fraction, and store it as a unique key string (`"dy/dx"`) inside a HashMap.
 * * Complexity:
 * Time Complexity: O(N^2 * log(MaxCoordinate))
 * Space Complexity: O(N)
 */
public class MaxPointsOnALine {
    private static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxGlobalPoints = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int localMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int slopeGcd = gcd(dx, dy);
                dx /= slopeGcd;
                dy /= slopeGcd;

                // Handle sign variance anomalies uniformly
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = Math.abs(dy); // Handle vertical slope exceptions
                }

                String slopeKey = dy + "/" + dx;
                slopeMap.put(slopeKey, slopeMap.getOrDefault(slopeKey, 0) + 1);
                localMax = Math.max(localMax, slopeMap.get(slopeKey));
            }
            maxGlobalPoints = Math.max(maxGlobalPoints, localMax + 1); // Add 1 to include the origin point itself
        }
        return maxGlobalPoints;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println("Maximum points on a shared line: " + maxPoints(points)); // 3
    }
}