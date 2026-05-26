import java.util.HashMap;

public class MaxPointsOnLine {
    public static int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int maxPoints = 0;

        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> slopes = new HashMap<>();
            int duplicates = 1;
            int currentMax = 0;

            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                // Standardize the slope fraction to avoid floating point inaccuracies
                String slope = (dx / gcd) + "/" + (dy / gcd);
                
                slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, slopes.get(slope));
            }
            maxPoints = Math.max(maxPoints, currentMax + duplicates);
        }
        return maxPoints;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println("Max points on a single line: " + maxPoints(points));
    }
}