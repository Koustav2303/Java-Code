import java.util.Arrays;

public class KClosestPoints {
    public static int[][] kClosest(int[][] points, int k) {
        // Sort the array based on the squared distance to origin (x^2 + y^2)
        Arrays.sort(points, (p1, p2) -> {
            int dist1 = p1[0] * p1[0] + p1[1] * p1[1];
            int dist2 = p2[0] * p2[0] + p2[1] * p2[1];
            return Integer.compare(dist1, dist2);
        });

        // Copy the first K elements
        return Arrays.copyOfRange(points, 0, k);
    }

    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        System.out.println("Points: " + Arrays.deepToString(points));
        
        int[][] closest = kClosest(points, k);
        System.out.println(k + " closest points to origin: " + Arrays.deepToString(closest));
    }
}