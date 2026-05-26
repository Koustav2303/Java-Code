import java.util.Arrays;

public class MinArrowsBalloons {
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        
        // Sort by END coordinate (use Integer.compare to avoid overflow bugs)
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int arrows = 1;
        int currentEnd = points[0][1];
        
        for (int i = 1; i < points.length; i++) {
            // If the balloon starts AFTER our current arrow's path, we need a new arrow
            if (points[i][0] > currentEnd) {
                arrows++;
                currentEnd = points[i][1];
            }
        }
        return arrows;
    }

    public static void main(String[] args) {
        int[][] balloons = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("Minimum arrows required: " + findMinArrowShots(balloons)); // Output: 2
    }
}