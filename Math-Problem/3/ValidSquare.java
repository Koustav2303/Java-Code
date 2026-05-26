import java.util.HashSet;

public class ValidSquare {
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> distances = new HashSet<>();
        
        distances.add(getSquaredDistance(p1, p2));
        distances.add(getSquaredDistance(p1, p3));
        distances.add(getSquaredDistance(p1, p4));
        distances.add(getSquaredDistance(p2, p3));
        distances.add(getSquaredDistance(p2, p4));
        distances.add(getSquaredDistance(p3, p4));
        
        // A valid square has exactly 2 unique distances (the sides and the diagonals)
        // Also ensure no points are exactly on top of each other (distance 0)
        return !distances.contains(0) && distances.size() == 2;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy; // We skip Math.sqrt to avoid precision issues
    }

    public static void main(String[] args) {
        int[] p1 = {0, 0}, p2 = {1, 1}, p3 = {1, 0}, p4 = {0, 1};
        System.out.println("Do the points form a square? " + validSquare(p1, p2, p3, p4));
    }
}